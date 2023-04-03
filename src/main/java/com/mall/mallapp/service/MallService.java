package com.mall.mallapp.service;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.ScanPolicy;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.mall.mallapp.DBConfig.AerospikeDB;
import com.mall.mallapp.model.Mall;


import java.util.ArrayList;
import java.util.List;

public class MallService {

    String namespace = "test";
    String set = "mall";

    public List<Mall> getAllMalls()
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
                System.out.println("Key: " + key.userKey.toString() + " - " + record.toString());
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

    public Mall getMall(int id)
    {
        Key key = new Key("test", "mall", id);
        Record record = AerospikeDB.getClient().get(null, key);
        if(record==null)
            return null;
        return new Mall(key.userKey.toInteger(),record.getString("name"),record.getString("address"),record.getInt("NumOfFloors"), record.getString("desc"));

    }
}
