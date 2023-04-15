package com.mall.mallapp.DTO;

import com.google.gson.Gson;

public class ItemDTO {
    String name;
    int price;
    String desc;
    int sale_pers;

    public ItemDTO(){};
    public ItemDTO(String name, int price, String desc, int sale_pers) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.sale_pers = sale_pers;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSale_pers() {
        return sale_pers;
    }

    public void setSale_pers(int sale_pers) {
        this.sale_pers = sale_pers;
    }
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
