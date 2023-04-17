package com.mall.mallapp.reposotry;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.query.RecordSet;
import com.aerospike.client.query.Statement;
import com.mall.mallapp.dBConfig.AerospikeDB;
import com.mall.mallapp.model.Shop;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * A repository class for managing the data of shops in the mall application.
 */
public class ShopRepo {

    /**
     * The namespace of the Aerospike database where the shops are stored.
     */
    private String namespace = "test";
    /**
     * The set of the Aerospike database where the shops are stored.
     */
    private String set = "shop";

    /**
     * The ID of the next shop that will be added to the database.
     */
    private int nextId;

    /**
     * Initializes a new instance of the {@link ShopRepo} class.
     */
    public ShopRepo()
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
     * Returns a list of all the shops in the given mall and floor.
     *
     * @param mall_id  the ID of the mall
     * @param floor_id the ID of the floor
     * @return a list of {@link Shop} objects
     */
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

    /**
     * Returns the shop with the given ID.
     *
     * @param shop_id the ID of the shop
     * @return the {@link Shop} object with the given ID, or {@code null} if no such object exists
     */
    public Shop getShop(int shop_id)
    {
        Key key = new Key(namespace,set, shop_id);
        Record record = AerospikeDB.getClient().get(null, key);
        if(record==null) {
            return null;
        }
        return new Shop(key.userKey.toInteger(),record.getInt("floor_id"),record.getInt("mall_id"),record.getString("shop_name"), record.getString("desc"),record.getString("opening_hours"));
    }

    /**
     *Adds a new shop to the database with the given mall ID, floor ID, and shop object.
     * Sets the shop_id of the shop object to the next available ID and updates the floor_id and mall_id fields.
     * @param mallId The ID of the mall in which the shop is located
     * @param floorId The ID of the floor on which the shop is located
     * @param shop The Shop object to be added to the database
     * @return The Shop object that was added to the database
     *
     */
    public Shop addShop(int mallId , int floorId , Shop shop)
    {
        shop.setShop_id(nextId);
        shop.setFloor_id(floorId);
        shop.setMall_id(mallId);
        WritePolicy writePolicy = new WritePolicy();
        writePolicy.sendKey = true;
        Key key = new Key(namespace , set, nextId);
        binsUpdateCreate(shop,key, writePolicy);
        return shop;
    }

    /**
     * Updates an existing shop in the database with the given ID and new shop object.
     *
     * @param id The ID of the shop to be updated
     *
     * @param shop The new Shop object to replace the existing shop object in the database
     */

    public void updateShop(int id , Shop shop)
    {
        Key key = new Key(namespace, set, id);

        WritePolicy updatePolicy = new WritePolicy();
        updatePolicy.recordExistsAction = RecordExistsAction.UPDATE_ONLY;

        binsUpdateCreate(shop, key, updatePolicy);
    }

    /**
     *Deletes the shop with the given ID from the database.
     * @param id The ID of the shop to be deleted
     * @return A string indicating whether the deletion was successful or not
     */

    public String deleteShop(int id)
    {
        Key key = new Key(namespace,set, id);
        WritePolicy deletePolicy = new WritePolicy();
        deletePolicy.durableDelete = true;
        AerospikeDB.getClient().delete(null , key);
        return "Deleted successfully";
    }

    /**
     *Updates or creates a record in the database with the given Shop object and key.
     *
     * The Shop object's fields are added as bins to the record.
     *
     * @param shop The Shop object to be added or updated in the database
     *
     * @param key The key of the record to be updated or created in the database
     *
     * @param Policy The WritePolicy to be used for updating or creating the record
     */

    private void binsUpdateCreate(Shop shop, Key key, WritePolicy Policy) {
        Bin floor_id = new Bin("floor_id" , shop.getFloor_id());
        Bin mall_id = new Bin("mall_id" , shop.getMall_id());
        Bin shop_name = new Bin ("shop_name" , shop.getShop_name());
        Bin desc = new Bin("desc", shop.getDesc());
        Bin opening_hours = new Bin("opening_hours", shop.getOpening_hours());

        AerospikeDB.getClient().put(Policy,key,floor_id,mall_id,shop_name,desc,opening_hours);
    }
}
