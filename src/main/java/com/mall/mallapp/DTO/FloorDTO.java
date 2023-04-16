/**

 The FloorDTO class represents a Data Transfer Object (DTO) for a floor in a mall.
 It contains the floor number, the mall ID, category, and number of shops.
 */

package com.mall.mallapp.DTO;

import com.google.gson.Gson;

public class FloorDTO {

    int mall_id;
    int floor_number;

    String category;
    int number_of_shops;

    /**
     * Default constructor for creating an empty instance of the class.
     */
    public FloorDTO(){}

    /**
     * Constructor for creating an instance of the class with provided values.
     *
     * @param mall_id the ID of the mall where the floor is located
     * @param floor_number the floor number
     * @param category the category of the floor
     * @param number_of_shops the number of shops on the floor
     */
    public FloorDTO(int mall_id, int floor_number, String category, int number_of_shops) {
        this.mall_id = mall_id;
        this.floor_number = floor_number;
        this.category = category;
        this.number_of_shops = number_of_shops;
    }

    /**
     * Returns the mall ID.
     *
     * @return mall_id the ID of the mall where the floor is located
     */
    public int getMall_id() {
        return mall_id;
    }

    /**
     * Sets the mall ID.
     *
     * @param mall_id the ID of the mall where the floor is located
     */
    public void setMall_id(int mall_id) {
        this.mall_id = mall_id;
    }

    /**
     * Returns the floor number.
     *
     * @return floor_number the floor number
     */
    public int getFloor_number() {
        return floor_number;
    }

    /**
     * Sets the floor number.
     *
     * @param floor_number the floor number
     */
    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    /**
     * Returns the category of the floor.
     *
     * @return category the category of the floor
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the floor.
     *
     * @param category the category of the floor
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the number of shops on the floor.
     *
     * @return number_of_shops the number of shops on the floor
     */
    public int getNumber_of_shops() {
        return number_of_shops;
    }

    /**
     * Sets the number of shops on the floor.
     *
     * @param number_of_shops the number of shops on the floor
     */
    public void setNumber_of_shops(int number_of_shops) {
        this.number_of_shops = number_of_shops;
    }

    /**
     * Returns a JSON representation of the object.
     *
     * @return a JSON string representation of the object
     */
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Checks if the object is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof FloorDTO)) {
            return false;
        }

        FloorDTO other = (FloorDTO) obj;

        boolean b = mall_id== other.mall_id
                && floor_number== other.floor_number
                && number_of_shops== other.number_of_shops
                && category.equals(other.category) ;
        System.out.println(b);
        return b;
    }
}
