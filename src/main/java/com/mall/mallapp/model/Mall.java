package com.mall.mallapp.model;

public class Mall {

    int id;
    String name ;
    String address;
    int number_of_floors;
    String description;

    public Mall(){};
    public Mall(int id, String name, String address, int number_of_floors, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.number_of_floors = number_of_floors;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
