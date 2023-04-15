package com.mall.mallapp.DTO;

import com.google.gson.Gson;
import com.mall.mallapp.model.Mall;

public class MallDTO {
    String name ;
    String address;
    int number_of_floors;
    String description;

    public MallDTO(){};

    public MallDTO(String name, String address, int number_of_floors, String description) {
        this.name = name;
        this.address = address;
        this.number_of_floors = number_of_floors;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber_of_floors() {
        return number_of_floors;
    }

    public void setNumber_of_floors(int number_of_floors) {
        this.number_of_floors = number_of_floors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
