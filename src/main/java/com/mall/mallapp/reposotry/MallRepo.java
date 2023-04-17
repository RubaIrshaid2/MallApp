package com.mall.mallapp.reposotry;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.mall.mallapp.dBConfig.AerospikeDB;
import com.mall.mallapp.exception.ObjectExistsException;
import com.mall.mallapp.model.Mall;
import java.util.ArrayList;
import java.util.List;
/**
 * The MallRepo class represents a repository for CRUD operations on Mall objects in the Aerospike database.
 */
public class MallRepo {

    // The namespace where the data is stored in the Aerospike database
    String namespace = "test";
    // The set where the Mall objects are stored in the Aerospike database
    String set = "mall";
    /**
     * The ID of the next Mall object that will be added to the Aerospike database
     */
    int nextId;

    List<Mall> mallsInDataBase = new ArrayList<>();
    /**
     * Constructs a new MallRepo object.
     * Initializes the next_id field with the highest ID of the Mall objects already in the database.
     */
    public MallRepo()
    {
        Statement statement = new Statement();

        statement.setNamespace(namespace);
        statement.setSetName(set);

        int maxi = 0 ;
        try{
            RecordSet records = AerospikeDB.getClient().query(null , statement);
            while (records.next()) {
                Key key = records.getKey();
                Record record = records.getRecord();
                Mall newMall = new Mall(key.userKey.toInteger(),record.getString("name"),record.getString("address"),record.getInt("NumOfFloors"), record.getString("desc"));
                mallsInDataBase.add(newMall);
                maxi = Math.max(key.userKey.toInteger(),maxi);
            }
            records.close();
        }
        catch (AerospikeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        nextId = maxi+1;
    }
    /**
     * Retrieves a list of all Mall objects in the Aerospike database.
     * @return a list of all Mall objects in the Aerospike database
     */
    public List<Mall> GetMalls()
    {
        List<Mall> mallList = new ArrayList<Mall>() ;
        Statement statement = new Statement();
        statement.setNamespace(namespace);
        statement.setSetName(set);
        RecordSet records = AerospikeDB.getClient().query(null , statement);
        try{
            while (records.next()) {
                Key key = records.getKey();
                Record record = records.getRecord();
                Mall newMall = new Mall(key.userKey.toInteger(),record.getString("name"),record.getString("address"),record.getInt("NumOfFloors"), record.getString("desc"));
                mallList.add(newMall);
            }
            records.close();
        }
        catch (AerospikeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return mallList;
    }


    /**
     * Retrieves the Mall object with the given ID from the Aerospike database.
     * @param id the ID of the Mall object to retrieve
     * @return the Mall object with the given ID, or null if no such object exists
     */
    public Mall GetMall(int id)
    {
        Key key = new Key(namespace, set, id);
        Record record = AerospikeDB.getClient().get(null, key);
        if(record==null) {
            return null;
        }
        return new Mall(key.userKey.toInteger(),record.getString("name"),record.getString("address"),record.getInt("NumOfFloors"), record.getString("desc"));
    }

    /**
     * Adds a new Mall object to the Aerospike database.
     * @param mall the Mall object to add
     * @return the added Mall object
     */
    public Mall AddMAll(Mall mall) throws ObjectExistsException
    {
        boolean found = mallsInDataBase.stream().anyMatch(m -> m.getName().equals(mall.getName()));
        if(!found) {
            mall.setId(nextId);
            WritePolicy writePolicy = new WritePolicy();
            writePolicy.sendKey = true;
            Key key = new Key(namespace, set, nextId);
            binsUpdateCreate(mall, key, writePolicy);
            nextId++;
            mallsInDataBase.add(mall);
            return mall;
        }
        throw new ObjectExistsException("the mall is already exist");
    }

    /**

     Updates the details of a mall with the given ID in the Aerospike database.

     @param id The ID of the mall to be updated.

     @param mall The updated mall object containing the new details.
     */
    public void UpdateMall(int id , Mall mall)
    {
        Key key = new Key(namespace, set, id);
        WritePolicy updatePolicy = new WritePolicy();
        updatePolicy.recordExistsAction = RecordExistsAction.UPDATE_ONLY;
        binsUpdateCreate(mall, key, updatePolicy);
    }

    /**
     * Deletes the mall with the given ID from the Aerospike database.
     *
     * @param id The ID of the mall to be deleted.
     *
     * @return A string indicating whether the deletion was successful or not.
     */
    public String DeleteMall(int id)
    {
        Key key = new Key(namespace,set, id);
        WritePolicy deletePolicy = new WritePolicy();
        deletePolicy.durableDelete = true;
        AerospikeDB.getClient().delete(null , key);
        return "Deleted successfully";
    }

    /**
     *
     *Helper method to create and update bins for a mall in the Aerospike database.
     *
     * @param mall The mall object containing the updated details.
     *
     * @param key The key of the mall in the database.
     *
     * @param Policy The write policy to be used for updating the mall details.
     */
    private void binsUpdateCreate(Mall mall, Key key, WritePolicy Policy) {
        Bin name = new Bin("name" , mall.getName());
        Bin address = new Bin("address" , mall.getAddress());
        Bin numOfFloors = new Bin ("NumOfFloors" , mall.getNumberOfFloors());
        Bin desc = new Bin("desc", mall.getDescription());
        AerospikeDB.getClient().put(Policy,key,name,address,numOfFloors,desc);
    }
}
