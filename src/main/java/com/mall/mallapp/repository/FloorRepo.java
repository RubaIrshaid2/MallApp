package com.mall.mallapp.repository;
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
import com.mall.mallapp.model.Floor;
import java.util.ArrayList;
import java.util.List;
/**
 * This is a Java class for the Floor repository which interacts with the Aerospike database.
 * The repository has methods for retrieving, adding, updating, and deleting Floor objects from the database.
 *  The FloorRepo class represents a repository for storing and managing floor records in an Aerospike database.
 */
public class FloorRepo {
    /**
     *The namespace of the Aerospike database.
     */
    private String namespace = "test";
    /**
     *The set name of the Aerospike database.
     */
    private String set = "floor";
    /**
     The next ID to use for a new record.
     */
    private int nextId;
    private List<Floor> floorsInDatabase = new ArrayList<>();
    /**
     *Constructs a new FloorRepo object and initializes the next ID by querying the Aerospike database for the highest
     *existing ID.
     */
    public FloorRepo()
    {
        int maxi = 0 ;
        Statement statement = new Statement();

        statement.setNamespace(namespace);
        statement.setSetName(set);
        RecordSet records = AerospikeDB.getClient().query(null , statement);
        try{
            while (records.next()) {
                Key key = records.getKey();
                Record record = records.getRecord();
                Floor newFloor = new Floor(key.userKey.toInteger(), record.getInt("mall_id"), record.getInt("floor_number"), record.getString("category"), record.getInt("NumOfShops"));
                floorsInDatabase.add(newFloor);
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
     *Returns a list of all floors in the specified mall.
     *@param mallId the ID of the mall to retrieve floors from.
     *@return a list of all floors in the specified mall.
     */
    public List<Floor> getFloors(int mallId)
    {
        List<Floor> FloorList = new ArrayList<Floor>() ;

        Statement statement = new Statement();

        statement.setNamespace(namespace);
        statement.setSetName(set);
        RecordSet records = AerospikeDB.getClient().query(null , statement);
        try{
            while (records.next()) {
                Key key = records.getKey();
                Record record = records.getRecord();
                if(record.getInt("mall_id") == mallId) {
                    Floor newFloor = new Floor(key.userKey.toInteger(), record.getInt("mall_id"), record.getInt("floor_number"), record.getString("category"), record.getInt("NumOfShops"));
                    FloorList.add(newFloor);
                }
            }
            records.close();
        }
        catch (AerospikeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return FloorList;
    }
    /**
     *Returns the floor with the specified ID and mall ID.
     *@param mall_id the ID of the mall the floor belongs to.
     *@param floor_number the ID of the floor.
     *@return the floor with the specified ID and mall ID, or null if no such floor exists.
     */
    public Floor getFloor(int mall_id , int floor_number)
    {

        Floor floorInMall = null;

        Statement statement = new Statement();

        statement.setNamespace(namespace);
        statement.setSetName(set);

        RecordSet records = AerospikeDB.getClient().query(null , statement);

        try{
            while (records.next()) {
                Key key = records.getKey();
                Record record = records.getRecord();

                if (record.getInt("mall_id") == mall_id &&  record.getInt("floor_number") == floor_number) {
                    floorInMall = new Floor(key.userKey.toInteger(), record.getInt("mall_id"), record.getInt("floor_number"), record.getString("category"), record.getInt("NumOfShops"));
                    break;
                }
            }
            records.close();
        }
        catch (AerospikeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return floorInMall;
    }

    /**
     *Adds a new floor to the specified mall.
     * @param mallId the ID of the mall to add the floor to.
     * @param floor the floor to add.
     * @return the added floor.
     */
    public Floor addFloor(int mallId , Floor floor) throws ObjectExistsException
    {
        boolean found = floorsInDatabase.stream().anyMatch(f -> f.getFloorNumber() == floor.getFloorNumber() && f.getMallId() == mallId);

        if(!found) {
            floor.setId(nextId);
            floor.setMallId(mallId);
            WritePolicy writePolicy = new WritePolicy();
            writePolicy.sendKey = true;
            Key key = new Key(namespace, set, nextId);
            binsUpdateCreate(floor, key, writePolicy);
            nextId++;
            return floor;
        }
        throw new ObjectExistsException("the Floor is already exist");
    }
    /**
     * Updates the floor with the specified ID and mall ID in the specified mall.
     * @param mallId the ID of the mall the floor belongs to.
     * @param id the ID of the floor.
     * @param floor the updated floor object.
     */
    public void updateFloor(int mallId ,int id , Floor floor)
    {
        floor.setMallId(mallId);
        Key key = new Key(namespace, set, id);

        WritePolicy updatePolicy = new WritePolicy();
        updatePolicy.recordExistsAction = RecordExistsAction.UPDATE_ONLY;

        binsUpdateCreate(floor, key, updatePolicy);
    }
    /**
     * Deletes the floor with the specified ID.
     * @param id the ID of the floor to delete.
     * @return a string indicating whether the deletion was successful.
     */
    public String deleteFloor(int id)
    {
        Key key = new Key(namespace,set, id);
        WritePolicy deletePolicy = new WritePolicy();
        deletePolicy.durableDelete = true;
        AerospikeDB.getClient().delete(null , key);
        return "Deleted successfully";
    }
    /**
     *
     * Updates or creates bins for the specified floor object and key using the specified write policy.
     * @param floor the floor object to update or create bins for.
     * @param key the key to use for the update or create operation.
     * @param Policy the write policy to use for the update or create operation.
     */
    private void binsUpdateCreate(Floor floor, Key key, WritePolicy Policy) {
        Bin floor_number = new Bin("floor_number" , floor.getFloorNumber());
        Bin category = new Bin("category" , floor.getCategory());
        Bin NumOfShops = new Bin ("NumOfShops" , floor.getNumberOfShops());
        Bin mall_id = new Bin("mall_id", floor.getMallId());

        AerospikeDB.getClient().put(Policy,key,floor_number,category,NumOfShops,mall_id);
    }
}
