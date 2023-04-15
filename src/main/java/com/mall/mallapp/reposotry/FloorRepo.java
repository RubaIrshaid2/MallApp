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

    String namespace = "test";
    String set = "floor";
    int next_id;

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

    public void updateFloor(int mall_id ,int id , Floor floor)
    {
        floor.setMall_id(mall_id);
        Key key = new Key(namespace, set, id);

        WritePolicy updatePolicy = new WritePolicy();
        updatePolicy.recordExistsAction = RecordExistsAction.UPDATE_ONLY;

        bins_update_create(floor, key, updatePolicy);
    }

    public String deleteFloor(int id)
    {
        Key key = new Key(namespace,set, id);
        WritePolicy deletePolicy = new WritePolicy();
        deletePolicy.durableDelete = true;
        AerospikeDB.getClient().delete(null , key);
        return "Deleted successfully";
    }
    private void bins_update_create(Floor floor, Key key, WritePolicy Policy) {
        Bin floor_number = new Bin("floor_number" , floor.getFloor_number());
        Bin category = new Bin("category" , floor.getCategory());
        Bin NumOfShops = new Bin ("NumOfShops" , floor.getNumber_of_shops());
        Bin mall_id = new Bin("mall_id", floor.getMall_id());

        AerospikeDB.getClient().put(Policy,key,floor_number,category,NumOfShops,mall_id);
    }
}
