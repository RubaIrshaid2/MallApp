package com.mall.mallapp.model;
/**
 * A class representing a mall.
 */
public class Mall {

    private int id;
    private String name ;
    private String address;
    private int numberOfFloors;
    private String description;

    /**
     * Constructs a new instance of the {@link Mall} class.
     */
    public Mall(){};

    /**
     * Constructs a new instance of the {@link Mall} class.
     * @param id The ID of the mall.
     * @param name The name of the mall.
     * @param address The address of the mall.
     * @param numberOfFloors The number of floors in the mall.
     * @param description The description of the mall.
     */
    public Mall(int id, String name, String address, int numberOfFloors, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.description = description;
    }

    /**
     * Returns the ID of the mall.
     * @return The ID of the mall.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the mall.
     * @param id The ID of the mall.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the mall.
     * @return The name of the mall.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the mall.
     * @param name The name of the mall.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address of the mall.
     * @return The address of the mall.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the mall.
     * @param address The address of the mall.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the number of floors in the mall.
     * @return The number of floors in the mall.
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Sets the number of floors in the mall.
     * @param numberOfFloors The number of floors in the mall.
     */
    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    /**
     * Returns the description of the mall.
     * @return The description of the mall.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the mall.
     * @param description The description of the mall.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks whether this instance of {@link Mall} is equal to another object.
     * @param obj The object to compare to.
     * @return {@code true} if the object is equal to this instance of {@link Mall}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Mall)) {
            return false;
        }

        Mall other = (Mall) obj;

        boolean b = name.equals(other.name) && address.equals(other.address) &&
                numberOfFloors == other.numberOfFloors &&
                description.equals(other.description);
        System.out.println(b);
        return b;
    }
}
