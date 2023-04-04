package com.mall.mallapp.service;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.mall.mallapp.DBConfig.AerospikeDB;
import com.mall.mallapp.model.Floor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FloorService {

    String namespace = "test";
    String set = "floor";
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
}
