package com.mall.mallapp.model;

public class Floor {

    int id;
    int floor_number;
    String category;
    int number_of_shops;

    public Floor(int id, int floor_number, String category, int number_of_shops) {
        this.id = id;
        this.floor_number = floor_number;
        this.category = category;
        this.number_of_shops = number_of_shops;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumber_of_shops() {
        return number_of_shops;
    }

    public void setNumber_of_shops(int number_of_shops) {
        this.number_of_shops = number_of_shops;
    }
}
