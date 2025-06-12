package com.mall.mallapp.dto;
import com.google.gson.Gson;

/**
 *  The FloorDTO class represents a Data Transfer Object (DTO) for a floor in a mall.
 *  It contains the floor number, the mall ID, category, and number of shops.
 */
public class FloorDTO {
    private int mallId;
    private int floorNumber;
    private String category;
    private int numberOfShops;
    /**
     * Default Constructor For creating Empty instance of the class.
     */
    public FloorDTO(){}

    /**
     * Constructor for creating an instance of the class with provided values.
     *
     * @param mallId The ID of the mall where the floor is located
     * @param floorNumber the floor number
     * @param category the category of the floor
     * @param numberOfShops the number of shops in the floor
     */
    public FloorDTO(int mallId, int floorNumber, String category, int numberOfShops) {
        this.mallId = mallId;
        this.floorNumber = floorNumber;
        this.category = category;
        this.numberOfShops = numberOfShops;
    }

    /**
     * Returns the mall ID.
     *
     * @return mall_id the ID of the mall where the floor is located
     */
    public int getMallId() {
        return mallId;
    }

    /**
     * Sets the mall ID.
     *
     * @param mallId the ID of the mall where the floor is located
     */
    public void setMallId(int mallId) {
        this.mallId = mallId;
    }

    /**
     * Returns the floor number.
     *
     * @return floor_number the floor number
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Sets the floor number.
     *
     * @param floorNumber the floor number
     */
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
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
    public int getNumberOfShops() {
        return numberOfShops;
    }

    /**
     * Sets the number of shops on the floor.
     *
     * @param numberOfShops the number of shops on the floor
     */
    public void setNumberOfShops(int numberOfShops) {
        this.numberOfShops = numberOfShops;
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
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof FloorDTO)) {
            return false;
        }

        FloorDTO other = (FloorDTO) obj;

        boolean b = mallId == other.mallId
                && floorNumber == other.floorNumber
                && numberOfShops == other.numberOfShops
                && category.equals(other.category) ;
        System.out.println(b);
        return b;
    }
}
