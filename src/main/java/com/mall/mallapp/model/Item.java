package com.mall.mallapp.model;
/**
 *  The Item class represents an item for sale in a shop.
 *  It contains information such as the item ID, the ID of the shop selling the item,
 *  the item name, price, description and sale percentage.
 */
public class Item {
    private int id;
    private int shopId;
    private String name;
    private int price;
    private String desc;
    private int salePers;

    public Item(){}
    /**
     * Constructs a new Item object with the given parameters.
     * @param id the item ID
     * @param shop_id the ID of the shop selling the item
     * @param name the name of the item
     * @param price the price of the item
     * @param desc the description of the item
     * @param sale_pers the sale percentage of the item
     */
    public Item(int id, int shop_id, String name, int price, String desc, int sale_pers) {
        this.id = id;
        this.shopId = shop_id;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.salePers = sale_pers;
    }

    /**
     * Returns the unique ID of the item.
     *
     * @return the unique ID of the item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the item.
     *
     * @param id the unique ID of the item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the ID of the shop where the item is sold.
     *
     * @return the ID of the shop where the item is sold
     */
    public int getShopId() {
        return shopId;
    }

    /**
     * Sets the ID of the shop where the item is sold.
     *
     * @param shopId the ID of the shop where the item is sold
     */
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the item.
     *
     * @return the price of the item
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     *
     * @param price the price of the item
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Returns the description of the item.
     *
     * @return the description of the item
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the description of the item.
     *
     * @param desc the description of the item
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *Returns the percentage of sale for this item.
     *@return an integer representing the percentage of sale for this item.
     */
    public int getSalePers() {
        return salePers;
    }

    /**

     *Sets the percentage of sale for this item.
     *@param salePers an integer representing the percentage of sale for this item.
     */
    public void setSalePers(int salePers) {
        this.salePers = salePers;
    }
}
