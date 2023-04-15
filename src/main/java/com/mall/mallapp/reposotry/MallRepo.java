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
import com.mall.mallapp.model.Mall;

import java.util.ArrayList;
import java.util.List;

public class MallRepo {

    String namespace = "test";
    String set = "mall";
    int next_id ;

    public MallRepo()
    {
        Statement statement = new Statement();

        statement.setNamespace(namespace);
        statement.setSetName(set);

        RecordSet records = AerospikeDB.getClient().query(null , statement);

        int maxi = 0 ;

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


    public Mall GetMall(int id)
    {
        Key key = new Key(namespace, set, id);
        Record record = AerospikeDB.getClient().get(null, key);
        if(record==null) {
            return null;
        }
        return new Mall(key.userKey.toInteger(),record.getString("name"),record.getString("address"),record.getInt("NumOfFloors"), record.getString("desc"));
    }

    public Mall AddMAll(Mall mall)
    {
        mall.setId(next_id);
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.sendKey = true;
        Key key = new Key(namespace , set , next_id);
        bins_update_create(mall, key, writePolicy);
        next_id++;
        return mall;
    }

    public void UpdateMall(int id , Mall mall)
    {
        Key key = new Key(namespace, set, id);

        WritePolicy updatePolicy = new WritePolicy();
        updatePolicy.recordExistsAction = RecordExistsAction.UPDATE_ONLY;

        bins_update_create(mall, key, updatePolicy);
    }

    public String DeleteMall(int id)
    {
        Key key = new Key(namespace,set, id);
        WritePolicy deletePolicy = new WritePolicy();
        deletePolicy.durableDelete = true;
        AerospikeDB.getClient().delete(null , key);
        return "Deleted successfully";
    }
    private void bins_update_create(Mall mall, Key key, WritePolicy Policy) {
        Bin name = new Bin("name" , mall.getName());
        Bin address = new Bin("address" , mall.getAddress());
        Bin numOfFloors = new Bin ("NumOfFloors" , mall.getNumber_of_floors());
        Bin desc = new Bin("desc", mall.getDescription());

        AerospikeDB.getClient().put(Policy,key,name,address,numOfFloors,desc);
    }
}