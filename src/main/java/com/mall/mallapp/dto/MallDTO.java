package com.mall.mallapp.dto;
import com.google.gson.Gson;

/**
 *  The MallDTO class represents a Data Transfer Object for a Mall entity.
 *  It contains information about the mall, such as its name, address, number of floors, and description.
 */
public class MallDTO {
    private String name ;
    private String address;
    private int numberOfFloors;
    private String description;

    /**
     * Default constructor for MallDTO.
     */
    public MallDTO(){};

    /**
     * Constructor for MallDTO that takes in parameters for all of the mall's attributes.
     * @param name The name of the mall.
     * @param address The address of the mall.
     * @param numberOfFloors The number of floors in the mall.
     * @param description A description of the mall.
     */

    public MallDTO(String name, String address, int numberOfFloors, String description) {
        this.name = name;
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.description = description;
    }

    /**
     * Getter method for the name of the mall.
     * @return The name of the mall.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of the mall.
     * @param name The new name for the mall.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the address of the mall.
     * @return The address of the mall.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for the address of the mall.
     * @param address The new address for the mall.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter method for the number of floors in the mall.
     * @return The number of floors in the mall.
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * Setter method for the number of floors in the mall.
     * @param numberOfFloors The new number of floors for the mall.
     */
    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    /**
     * Getter method for the description of the mall.
     * @return A description of the mall.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for the description of the mall.
     * @param description A new description for the mall.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Converts the MallDTO object to its JSON representation.
     * @return The JSON representation of the MallDTO object.
     */
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Checks whether this MallDTO object is equal to another object.
     * @param obj The object to compare against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MallDTO)) {
            return false;
        }

        MallDTO other = (MallDTO) obj;

        boolean b = name.equals(other.name) && address.equals(other.address) &&
                numberOfFloors == other.numberOfFloors &&
                description.equals(other.description);
        System.out.println(b);
        return b;
    }
}
