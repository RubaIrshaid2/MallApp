/**

 A class representing a Shop in a mall.
 */
package com.mall.mallapp.model;

public class Shop {
    int shop_id;
    int floor_id;
    int mall_id;
    String shop_name;
    String desc;
    String opening_hours;

    /**
     * Empty constructor for a Shop object.
     */
    public Shop(){}

    /**
     * Constructor for a Shop object.
     * @param shop_id the unique identifier for the shop
     * @param floor_id the identifier for the floor where the shop is located
     * @param mall_id the identifier for the mall where the shop is located
     * @param shop_name the name of the shop
     * @param desc the description of the shop
     * @param opening_hours the opening hours of the shop
     */
    public Shop(int shop_id, int floor_id, int mall_id, String shop_name, String desc, String opening_hours) {
        this.shop_id = shop_id;
        this.floor_id = floor_id;
        this.mall_id = mall_id;
        this.shop_name = shop_name;
        this.desc = desc;
        this.opening_hours = opening_hours;
    }

    /**
     * Returns the unique identifier for the shop.
     * @return the shop ID
     */
    public int getShop_id() {
        return shop_id;
    }


    /**
     * Sets the unique identifier for the shop.
     * @param shop_id the shop ID to set
     */
    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    /**
     * Returns the identifier for the floor where the shop is located.
     * @return the floor ID
     */
    public int getFloor_id() {
        return floor_id;
    }

    /**
     * Sets the identifier for the floor where the shop is located.
     * @param floor_id the floor ID to set
     */
    public void setFloor_id(int floor_id) {
        this.floor_id = floor_id;
    }

    /**
     * Returns the identifier for the mall where the shop is located.
     * @return the mall ID
     */
    public int getMall_id() {
        return mall_id;
    }

    /**
     * Sets the identifier for the mall where the shop is located.
     * @param mall_id the mall ID to set
     */
    public void setMall_id(int mall_id) {
        this.mall_id = mall_id;
    }

    /**
     * Returns the name of the shop.
     * @return the shop name
     */
    public String getShop_name() {
        return shop_name;
    }

    /**
     * Sets the name of the shop.
     * @param shop_name the shop name to set
     */
    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    /**
     * Returns the description of the shop.
     * @return the shop description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the description of the shop.
     * @param desc the shop description to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Returns the opening hours of the shop.
     * @return the opening hours
     */
    public String getOpening_hours() {
        return opening_hours;
    }

    /**
     * Returns the opening hours of the shop.
     * @return the opening hours
     */
    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }
}
