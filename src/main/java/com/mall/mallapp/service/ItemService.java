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
import com.mall.mallapp.model.Item;
import com.mall.mallapp.model.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemService {

    String namespace = "test";
    String set = "item";
    public List<Item> getItems(int shop_id) {

        List<Item> ItemList = new ArrayList<Item>() ;

        Statement statement = new Statement();

        statement.setNamespace(namespace);
        statement.setSetName(set);
        RecordSet records = AerospikeDB.getClient().query(null , statement);
        try{
            while (records.next()) {
                Key key = records.getKey();
                Record record = records.getRecord();
                Item newItem = new Item(key.userKey.toInteger(), record.getInt("shop_id"), record.getString("name"), record.getInt("price"), record.getString("desc"),record.getInt("sale_pers"));
                ItemList.add(newItem);
            }
            records.close();
        }
        catch (AerospikeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        List<Item> filteredList = ItemList.stream()
                .filter(item -> item.getShop_id() == shop_id)
                .collect(Collectors.toList());
        return filteredList;
    }

    public Item add_item(int shop_id ,Item item)
    {
        item.setId(2);
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.sendKey = true;
        Key key = new Key(namespace , set, 2 );
        item.setShop_id(shop_id);
        bins_update_create(item, key, writePolicy );
        return item;
    }

    public Item getItem(int item_id)
    {
        Key key = new Key(namespace,set, item_id);
        Record record = AerospikeDB.getClient().get(null, key);
        if(record==null) {
            return null;
        }
        return new Item(key.userKey.toInteger(), record.getInt("shop_id"), record.getString("name"), record.getInt("price"), record.getString("desc"),record.getInt("sale_pers"));
    }

    public void updateItem(int id , Item item)
    {
        Key key = new Key(namespace, set, id);

        WritePolicy updatePolicy = new WritePolicy();
        updatePolicy.recordExistsAction = RecordExistsAction.UPDATE_ONLY;

        bins_update_create(item, key, updatePolicy);
    }

    public String deleteItem(int id)
    {
        Key key = new Key(namespace,set, id);
        WritePolicy deletePolicy = new WritePolicy();
        deletePolicy.durableDelete = true;
        AerospikeDB.getClient().delete(null , key);
        return "Deleted successfully";
    }
    private void bins_update_create(Item item, Key key, WritePolicy Policy) {
        Bin shopId = new Bin("shop_id" , item.getShop_id());
        Bin name = new Bin("name" , item.getName());
        Bin price = new Bin ("price" , item.getPrice());
        Bin desc = new Bin("desc", item.getDesc());
        Bin sale_pers = new Bin("sale_pers" , item.getSale_pers());
        AerospikeDB.getClient().put(Policy,key,shopId,name,price,desc,sale_pers);
    }
}
