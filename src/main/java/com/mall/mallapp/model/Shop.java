package com.mall.mallapp.model;
/**
 *A class representing a Shop in a mall.
 */
public class Shop {
    private int shopId;
    private int floorId;
    private int mallId;
    private String shopName;
    private String desc;
    private String openingHours;
    /**
     * Empty constructor for a Shop object.
     */
    public Shop(){}

    /**
     * Constructor for a Shop object.
     * @param shopId the unique identifier for the shop
     * @param floorId the identifier for the floor where the shop is located
     * @param mallId the identifier for the mall where the shop is located
     * @param shopName the name of the shop
     * @param desc the description of the shop
     * @param openingHours the opening hours of the shop
     */
    public Shop(int shopId, int floorId, int mallId, String shopName, String desc, String openingHours) {
        this.shopId = shopId;
        this.floorId = floorId;
        this.mallId = mallId;
        this.shopName = shopName;
        this.desc = desc;
        this.openingHours = openingHours;
    }

    /**
     * Returns the unique identifier for the shop.
     * @return the shop ID
     */
    public int getShopId() {
        return shopId;
    }


    /**
     * Sets the unique identifier for the shop.
     * @param shopId the shop ID to set
     */
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    /**
     * Returns the identifier for the floor where the shop is located.
     * @return the floor ID
     */
    public int getFloorId() {
        return floorId;
    }

    /**
     * Sets the identifier for the floor where the shop is located.
     * @param floorId the floor ID to set
     */
    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    /**
     * Returns the identifier for the mall where the shop is located.
     * @return the mall ID
     */
    public int getMallId() {
        return mallId;
    }

    /**
     * Sets the identifier for the mall where the shop is located.
     * @param mallId the mall ID to set
     */
    public void setMallId(int mallId) {
        this.mallId = mallId;
    }

    /**
     * Returns the name of the shop.
     * @return the shop name
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Sets the name of the shop.
     * @param shopName the shop name to set
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * Returns the description of the shop.
     * @return the shop description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the description of the shop.
     * @param desc the shop description to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Returns the opening hours of the shop.
     * @return the opening hours
     */
    public String getOpeningHours() {
        return openingHours;
    }

    /**
     * Returns the opening hours of the shop.
     * @return the opening hours
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }
}
