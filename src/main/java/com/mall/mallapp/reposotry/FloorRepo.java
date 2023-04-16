/**
 * This is a Java class for the Floor repository which interacts with the Aerospike database.
 * The repository has methods for retrieving, adding, updating, and deleting Floor objects from the database.
 *  The FloorRepo class represents a repository for storing and managing floor records in an Aerospike database.
 */
package com.mall.mallapp.reposotry;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.mall.mallapp.DBConfig.AerospikeDB;
import com.mall.mallapp.model.Floor;

import java.util.ArrayList;
import java.util.List;

public class FloorRepo {

    /**
     *The namespace of the Aerospike database.
     */
    String namespace = "test";
    /**
     *The set name of the Aerospike database.
     */
    String set = "floor";
    /**
     The next ID to use for a new record.
     */
    int next_id;

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
                maxi = Math.max(key.userKey.toInteger(),maxi);
                }
            records.close();
        }
        catch (AerospikeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        next_id = maxi+1;
    }
    /**
     *Returns a list of all floors in the specified mall.
     *@param mall_id the ID of the mall to retrieve floors from.
     *@return a list of all floors in the specified mall.
     */
    public List<Floor> getFloors(int mall_id)
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
                if(record.getInt("mall_id") == mall_id) {
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
     * @param mall_id the ID of the mall to add the floor to.
     * @param floor the floor to add.
     * @return the added floor.
     */
    public Floor add_Floor(int mall_id ,Floor floor)
    {
        floor.setId(next_id);
        floor.setMall_id(mall_id);
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.sendKey = true;
        Key key = new Key(namespace , set, next_id);
        bins_update_create(floor, key, writePolicy);
        next_id++;
        return floor;
    }

    /**
     * Updates the floor with the specified ID and mall ID in the specified mall.
     * @param mall_id the ID of the mall the floor belongs to.
     * @param id the ID of the floor.
     * @param floor the updated floor object.
     */
    public void updateFloor(int mall_id ,int id , Floor floor)
    {
        floor.setMall_id(mall_id);
        Key key = new Key(namespace, set, id);

        WritePolicy updatePolicy = new WritePolicy();
        updatePolicy.recordExistsAction = RecordExistsAction.UPDATE_ONLY;

        bins_update_create(floor, key, updatePolicy);
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
    private void bins_update_create(Floor floor, Key key, WritePolicy Policy) {
        Bin floor_number = new Bin("floor_number" , floor.getFloor_number());
        Bin category = new Bin("category" , floor.getCategory());
        Bin NumOfShops = new Bin ("NumOfShops" , floor.getNumber_of_shops());
        Bin mall_id = new Bin("mall_id", floor.getMall_id());

        AerospikeDB.getClient().put(Policy,key,floor_number,category,NumOfShops,mall_id);
    }
}
