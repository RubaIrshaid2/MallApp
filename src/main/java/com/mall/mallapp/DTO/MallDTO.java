/**

 The MallDTO class represents a Data Transfer Object for a Mall entity.
 It contains information about the mall, such as its name, address, number of floors, and description.
 */
package com.mall.mallapp.DTO;

import com.google.gson.Gson;
import com.mall.mallapp.model.Mall;

public class MallDTO {
    String name ;
    String address;
    int number_of_floors;
    String description;

    /**
     * Default constructor for MallDTO.
     */
    public MallDTO(){};

    /**
     * Constructor for MallDTO that takes in parameters for all of the mall's attributes.
     * @param name The name of the mall.
     * @param address The address of the mall.
     * @param number_of_floors The number of floors in the mall.
     * @param description A description of the mall.
     */

    public MallDTO(String name, String address, int number_of_floors, String description) {
        this.name = name;
        this.address = address;
        this.number_of_floors = number_of_floors;
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
    public int getNumber_of_floors() {
        return number_of_floors;
    }

    /**
     * Setter method for the number of floors in the mall.
     * @param number_of_floors The new number of floors for the mall.
     */
    public void setNumber_of_floors(int number_of_floors) {
        this.number_of_floors = number_of_floors;
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MallDTO)) {
            return false;
        }

        MallDTO other = (MallDTO) obj;

        boolean b = name.equals(other.name) && address.equals(other.address) &&
                number_of_floors== other.number_of_floors &&
                description.equals(other.description);
        System.out.println(b);
        return b;
    }

}
