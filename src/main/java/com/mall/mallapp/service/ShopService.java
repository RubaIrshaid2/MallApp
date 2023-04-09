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
import com.mall.mallapp.model.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShopService {

    String namespace = "test";
    String set = "shop";
    public List<Shop> getShops(int mall_id , int floor_id) {

        List<Shop> ShopList = new ArrayList<Shop>() ;

        Statement statement = new Statement();

        statement.setNamespace(namespace);
        statement.setSetName(set);
        RecordSet records = AerospikeDB.getClient().query(null , statement);
        try{
            while (records.next()) {
                Key key = records.getKey();
                Record record = records.getRecord();
                Shop newShop = new Shop(key.userKey.toInteger(), record.getInt("floor_id"), record.getInt("mall_id"), record.getString("shop_name"), record.getString("desc"),record.getString("opening_hours"));
                ShopList.add(newShop);
            }
            records.close();
        }
        catch (AerospikeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        List<Shop> filteredList = ShopList.stream()
                .filter(shop -> shop.getMall_id() == mall_id && shop.getFloor_id() == floor_id)
                .collect(Collectors.toList());
        return filteredList;
    }

    public Shop add_shop(int mallId , int floorId , Shop shop)
    {
        shop.setShop_id(3);
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.sendKey = true;
        Key key = new Key(namespace , set, 3 );
        shop.setFloor_id(floorId);
        shop.setMall_id(mallId);
        bins_update_create(shop,key, writePolicy);
        return shop;
    }

    public Shop getShop(int shop_id)
    {
        Key key = new Key(namespace,set, shop_id);
        Record record = AerospikeDB.getClient().get(null, key);
        if(record==null) {
            return null;
        }
        return new Shop(key.userKey.toInteger(),record.getInt("floor_id"),record.getInt("mall_id"),record.getString("shop_name"), record.getString("desc"),record.getString("opening_hours"));
    }

    public void updateShop(int id , Shop shop)
    {
        Key key = new Key(namespace, set, id);

        WritePolicy updatePolicy = new WritePolicy();
        updatePolicy.recordExistsAction = RecordExistsAction.UPDATE_ONLY;

        bins_update_create(shop, key, updatePolicy);
    }

    public String deleteShop(int id)
    {
        Key key = new Key(namespace,set, id);
        WritePolicy deletePolicy = new WritePolicy();
        deletePolicy.durableDelete = true;
        AerospikeDB.getClient().delete(null , key);
        return "Deleted successfully";
    }

    private void bins_update_create(Shop shop, Key key, WritePolicy Policy) {
        Bin floor_id = new Bin("floor_id" , shop.getFloor_id());
        Bin mall_id = new Bin("mall_id" , shop.getMall_id());
        Bin shop_name = new Bin ("shop_name" , shop.getShop_name());
        Bin desc = new Bin("desc", shop.getDesc());
        Bin opening_hours = new Bin("opening_hours", shop.getOpening_hours());

        AerospikeDB.getClient().put(Policy,key,floor_id,mall_id,shop_name,desc,opening_hours);
    }
}
