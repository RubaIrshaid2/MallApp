package com.mall.mallapp.service;

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
import com.mall.mallapp.model.Mall;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FloorService {

    String namespace = "test";
    String set = "floor";
    int id = 3 ;
    public List<Floor> getFloors(int mall_id)
    {
        List<Floor> FloorList = new ArrayList<Floor>() ;

        Statement statement = new Statement();

        statement.setNamespace(namespace);
        statement.setSetName(set);
        System.out.println("lllllllllllllllllllllll");
        RecordSet records = AerospikeDB.getClient().query(null , statement);
        System.out.println("mmmmmmmmmmmmmm");
        try{
            while (records.next()) {
                Key key = records.getKey();
                Record record = records.getRecord();
                System.out.println("Key: " + key.userKey.toString() + " - " + record.toString());
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

        List<Floor> FloorList = new ArrayList<Floor>() ;

        Statement statement = new Statement();

        statement.setNamespace(namespace);
        statement.setSetName(set);

        RecordSet records = AerospikeDB.getClient().query(null , statement);

        try{
            while (records.next()) {
                Key key = records.getKey();
                Record record = records.getRecord();

                Floor newFloor = new Floor(key.userKey.toInteger(), record.getInt("mall_id"), record.getInt("floor_number"), record.getString("category"), record.getInt("NumOfShops"));
                FloorList.add(newFloor);
            }
            records.close();
        }
        catch (AerospikeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        List<Floor> filteredList = FloorList.stream()
                .filter(floor -> floor.getMall_id() == mall_id && floor.getFloor_number() == floor_number)
                .collect(Collectors.toList());
        return filteredList.get(0);
    }

    public Floor add_Floor(Floor floor)
    {
        floor.setId(4);
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.sendKey = true;
        Key key = new Key(namespace , set, 4);

        System.out.println("fsdkldjf");
        Bin floor_number = new Bin("floor_number" , floor.getFloor_number());
        Bin category = new Bin("category" , floor.getCategory());
        Bin NumOfShops = new Bin ("NumOfShops" , floor.getNumber_of_shops());
        Bin mall_id = new Bin("mall_id", 1);
        System.out.println("fffffffffffffffffffff");
        AerospikeDB.getClient().put(writePolicy,key,floor_number,category,NumOfShops,mall_id);
        System.out.println("gggggggggggggggggggggggggg");
        return floor;
    }

    public void updateFloor(int id , Floor floor)
    {
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
        System.out.println("lkjgx");
        AerospikeDB.getClient().delete(null , key);
        System.out.println("sldfslkfjsld");
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
