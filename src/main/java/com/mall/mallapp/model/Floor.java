/**

 The {@code Floor} class represents a floor in a shopping mall.
 It contains information about the floor such as its ID, mall ID,
 floor number, category and number of shops.
 <p>
 This class provides getters and setters for accessing and modifying
 its fields.
 */
package com.mall.mallapp.model;

public class Floor {

    int id;
    int mall_id;
    int floor_number;

    String category;
    int number_of_shops;

    /**
     * Constructs a new {@code Floor} object with default values.
     */
    public Floor(){}

    /**
     * Constructs a new {@code Floor} object with the given values.
     *
     * @param id the ID of the floor
     * @param mall_id the ID of the mall that the floor belongs to
     * @param floor_number the number of the floor
     * @param category the category of the floor (e.g. food court, retail)
     * @param number_of_shops the number of shops on the floor
     */
    public Floor(int id,int mall_id , int floor_number, String category, int number_of_shops) {
        this.id = id;
        this.mall_id = mall_id;
        this.floor_number = floor_number;
        this.category = category;
        this.number_of_shops = number_of_shops;
    }

    /**
     * Returns the ID of the floor.
     *
     * @return the ID of the floor
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the floor.
     *
     * @param id the new ID of the floor
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the ID of the mall that the floor belongs to.
     *
     * @return the ID of the mall that the floor belongs to
     */
    public int getMall_id() {
        return mall_id;
    }

    /**
     * Sets the ID of the mall that the floor belongs to.
     *
     * @param mall_id the new ID of the mall that the floor belongs to
     */
    public void setMall_id(int mall_id) {
        this.mall_id = mall_id;
    }

    /**
     * Returns the number of the floor.
     *
     * @return the number of the floor
     */
    public int getFloor_number() {
        return floor_number;
    }

    /**
     * Sets the number of the floor.
     *
     * @param floor_number the new number of the floor
     */
    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    /**
     * Returns the category of the floor.
     *
     * @return the category of the floor
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the floor.
     *
     * @param category the new category of the floor
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the number of shops on the floor.
     *
     * @return the number of shops on the floor
     */
    public int getNumber_of_shops() {
        return number_of_shops;
    }

    /**
     * Sets the number of shops on the floor.
     *
     * @param number_of_shops the new number of shops on the floor
     */
    public void setNumber_of_shops(int number_of_shops) {
        this.number_of_shops = number_of_shops;
    }
}
