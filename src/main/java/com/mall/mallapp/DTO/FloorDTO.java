package com.mall.mallapp.DTO;

import com.google.gson.Gson;

public class FloorDTO {

    int mall_id;
    int floor_number;

    String category;
    int number_of_shops;

    public FloorDTO(){}

    public FloorDTO(int mall_id, int floor_number, String category, int number_of_shops) {
        this.mall_id = mall_id;
        this.floor_number = floor_number;
        this.category = category;
        this.number_of_shops = number_of_shops;
    }

    public int getMall_id() {
        return mall_id;
    }

    public void setMall_id(int mall_id) {
        this.mall_id = mall_id;
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

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
