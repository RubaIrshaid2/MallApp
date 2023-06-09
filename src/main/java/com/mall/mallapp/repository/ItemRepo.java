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
import com.mall.mallapp.model.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * The ItemRepo class provides methods for performing database operations on the Item model in an Aerospike database.
 */
public class ItemRepo {
    /**
     * The namespace in the Aerospike database where the items are stored.
     */
    private String namespace = "test";
    /**
     * The name of the set in the Aerospike database where the items are stored.
     */
    private String set = "item";
    /**
     * The ID of the next item that will be added to the database.
     */
    private int nextId;
    /**
     * Constructs an ItemRepo object and sets the next_id field to the ID of the next item to be added to the database.
     * It does this by querying the database for all items and finding the maximum ID.
     */
    public ItemRepo()
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
        nextId = maxi+1;
    }

    /**
     * Gets a list of all items in the database for a given shop ID.
     *
     * @param shopId The ID of the shop whose items are being retrieved.
     * @return A list of all items in the database for the given shop ID.
     */
    public List<Item> getItems(int shopId) {

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
                .filter(item -> item.getShopId() == shopId)
                .collect(Collectors.toList());
        return filteredList;
    }

    /**
     * Gets an item with a given ID from the database.
     *
     * @param itemId The ID of the item to retrieve.
     * @return The item with the given ID, or null if the item does not exist in the database.
     */
    public Item getItem(int itemId)
    {
        Key key = new Key(namespace,set, itemId);
        Record record = AerospikeDB.getClient().get(null, key);
        if(record==null) {
            return null;
        }
        return new Item(key.userKey.toInteger(), record.getInt("shop_id"), record.getString("name"), record.getInt("price"), record.getString("desc"),record.getInt("sale_pers"));
    }

    /**
     *Adds a new item to the database with the given shop ID and returns the added item.
     *@param shopId the ID of the shop where the item is being added
     *@param item the item being added to the database
     *@return the added item
     */
    public Item addItem(int shopId , Item item)
    {
        System.out.println(shopId);
        item.setId(nextId);
        item.setShopId(shopId);
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.sendKey = true;
        Key key = new Key(namespace , set, nextId);
        binsUpdateCreate(item, key, writePolicy );
        nextId++;
        return item;
    }

    /**
     *
     *Updates an existing item in the database with the given ID and shop ID.
     *
     * @param id the ID of the item being updated
     *
     * @param shopId the ID of the shop where the item is being updated
     *
     * @param item the updated item information
     */
    public void updateItem(int id ,int shopId ,  Item item)
    {
        Key key = new Key(namespace, set, id);
        item.setShopId(shopId);
        WritePolicy updatePolicy = new WritePolicy();
        updatePolicy.recordExistsAction = RecordExistsAction.UPDATE_ONLY;

        binsUpdateCreate(item, key, updatePolicy);
    }

    /**
     * Deletes an existing item from the database with the given ID.
     * @param id the ID of the item being deleted
     * @return a message indicating the deletion status
     * */
    public String deleteItem(int id)
    {
        Key key = new Key(namespace,set, id);
        WritePolicy deletePolicy = new WritePolicy();
        deletePolicy.durableDelete = true;
        AerospikeDB.getClient().delete(null , key);
        return "Deleted successfully";
    }

    /**
     *
     * Helper method to update or create bins for an item in the database.
     * @param item the item being updated or added to the database
     * @param key the key for the item being updated or added to the database
     * @param Policy the write policy being used to update or add the item to the database
     */
    private void binsUpdateCreate(Item item, Key key, WritePolicy Policy) {
        Bin shopId = new Bin("shop_id" , item.getShopId());
        Bin name = new Bin("name" , item.getName());
        Bin price = new Bin ("price" , item.getPrice());
        Bin desc = new Bin("desc", item.getDesc());
        Bin sale_pers = new Bin("sale_pers" , item.getSalePers());
        AerospikeDB.getClient().put(Policy,key,shopId,name,price,desc,sale_pers);
    }
}
