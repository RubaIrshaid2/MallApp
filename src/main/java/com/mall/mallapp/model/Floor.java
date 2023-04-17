package com.mall.mallapp.model;
/**
 *  The {@code Floor} class represents a floor in a shopping mall.
 *  It contains information about the floor such as its ID, mall ID,
 *  floor number, category and number of shops.
 *  <p>
 *  This class provides getters and setters for accessing and modifying
 *  its fields.
 */
public class Floor {

    private int id;
    private int mallId;
    private int floorNumber;
    private String category;
    private int numberOfShops;

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
        this.mallId = mall_id;
        this.floorNumber = floor_number;
        this.category = category;
        this.numberOfShops = number_of_shops;
    }

    /**
     * Returns the ID of the floor.
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
    public int getMallId() {
        return mallId;
    }

    /**
     * Sets the ID of the mall that the floor belongs to.
     *
     * @param mallId the new ID of the mall that the floor belongs to
     */
    public void setMallId(int mallId) {
        this.mallId = mallId;
    }

    /**
     * Returns the number of the floor.
     *
     * @return the number of the floor
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Sets the number of the floor.
     *
     * @param floorNumber the new number of the floor
     */
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
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
    public int getNumberOfShops() {
        return numberOfShops;
    }

    /**
     * Sets the number of shops on the floor.
     *
     * @param numberOfShops the new number of shops on the floor
     */
    public void setNumberOfShops(int numberOfShops) {
        this.numberOfShops = numberOfShops;
    }
}
