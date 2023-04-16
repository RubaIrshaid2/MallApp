/**
 * Represents a data transfer object for a shop in a mall.
 */
package com.mall.mallapp.DTO;

import com.google.gson.Gson;
import com.mall.mallapp.model.Shop;

public class ShopDTO {

    int floor_id;
    int mall_id;
    String shop_name;
    String desc;
    String opening_hours;

    /**
     * Default constructor for the ShopDTO class.
     */
    public ShopDTO(){};

    /**
     * Constructor for the ShopDTO class.
     *
     * @param floor_id the ID of the floor where the shop is located
     * @param mall_id the ID of the mall where the shop is located
     * @param shop_name the name of the shop
     * @param desc the description of the shop
     * @param opening_hours the opening hours of the shop
     */
    public ShopDTO(int floor_id, int mall_id, String shop_name, String desc, String opening_hours) {
        this.floor_id = floor_id;
        this.mall_id = mall_id;
        this.shop_name = shop_name;
        this.desc = desc;
        this.opening_hours = opening_hours;
    }

    /**
     * Gets the ID of the floor where the shop is located.
     *
     * @return the floor ID
     */
    public int getFloor_id() {
        return floor_id;
    }

    /**
     * Sets the ID of the floor where the shop is located.
     *
     * @param floor_id the floor ID
     */
    public void setFloor_id(int floor_id) {
        this.floor_id = floor_id;
    }

    /**
     * Gets the ID of the mall where the shop is located.
     *
     * @return the mall ID
     */
    public int getMall_id() {
        return mall_id;
    }

    /**
     * Sets the ID of the mall where the shop is located.
     *
     * @param mall_id the mall ID
     */
    public void setMall_id(int mall_id) {
        this.mall_id = mall_id;
    }

    /**
     * Gets the name of the shop.
     *
     * @return the shop name
     */
    public String getShop_name() {
        return shop_name;
    }

    /**
     * Sets the name of the shop.
     *
     * @param shop_name the shop name
     */
    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    /**
     * Gets the description of the shop.
     *
     * @return the shop description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the description of the shop.
     *
     * @param desc the shop description
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Gets the opening hours of the shop.
     *
     * @return the shop opening hours
     */
    public String getOpening_hours() {
        return opening_hours;
    }

    /**
     * Sets the opening hours of the shop.
     *
     * @param opening_hours the shop opening hours
     */
    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    /**
     * Converts this object to a JSON string using the Gson library.
     *
     * @return the JSON string representation of this object
     */
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Compares this object with another object for equality.
     *
     * @param obj the object to compare with this object
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ShopDTO)) {
            return false;
        }

        ShopDTO other = (ShopDTO) obj;

        boolean b = floor_id == other.floor_id &&
                    mall_id == other.mall_id &&
                shop_name.equals(other.shop_name) &&
                desc.equals(other.desc) &&
                opening_hours.equals(other.opening_hours);
        System.out.println(b);
        return b;
    }
}
