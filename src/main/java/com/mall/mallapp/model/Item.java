package com.mall.mallapp.model;

public class Item {
    int id;
    int shop_id;
    String name;
    int price;
    String desc;
    int sale_pers;

    public Item(){}
    public Item(int id, int shop_id, String name, int price, String desc, int sale_pers) {
        this.id = id;
        this.shop_id = shop_id;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.sale_pers = sale_pers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
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
}
