
package com.mall.mallapp.DTO;
import com.google.gson.Gson;
/**
 * The `ItemDTO` class represents a data transfer object for an item in a shop. It contains
 * information about the item's name, price, description, and sale percentage.
 */

public class ItemDTO {
    private String name;
    private int price;
    private String desc;
    private int salePers;

    /**
     * Constructs a new `ItemDTO` with default values for its fields.
     */
    public ItemDTO(){};

    /**
     * Constructs a new `ItemDTO` with the specified values for its fields.
     *
     * @param name the name of the item
     * @param price the price of the item
     * @param desc the description of the item
     * @param sale_pers the sale percentage of the item
     */
    public ItemDTO(String name, int price, String desc, int sale_pers) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.salePers = sale_pers;
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
     * Returns the sale percentage of the item.
     *
     * @return the sale percentage of the item
     */
    public int getSalePers() {
        return salePers;
    }

    /**
     * Sets the sale percentage of the item.
     *
     * @param salePers the sale percentage of the item
     */
    public void setSalePers(int salePers) {
        this.salePers = salePers;
    }

    /**
     * Returns a JSON representation of this `ItemDTO` object.
     *
     * @return a JSON representation of this `ItemDTO` object
     */
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
