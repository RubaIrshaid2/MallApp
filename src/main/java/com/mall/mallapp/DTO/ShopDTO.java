package com.mall.mallapp.DTO;

import com.google.gson.Gson;
import com.mall.mallapp.model.Shop;

public class ShopDTO {

    int floor_id;
    int mall_id;
    String shop_name;
    String desc;
    String opening_hours;

    public ShopDTO(){};

    public ShopDTO(int floor_id, int mall_id, String shop_name, String desc, String opening_hours) {
        this.floor_id = floor_id;
        this.mall_id = mall_id;
        this.shop_name = shop_name;
        this.desc = desc;
        this.opening_hours = opening_hours;
    }

    public int getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(int floor_id) {
        this.floor_id = floor_id;
    }

    public int getMall_id() {
        return mall_id;
    }

    public void setMall_id(int mall_id) {
        this.mall_id = mall_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
