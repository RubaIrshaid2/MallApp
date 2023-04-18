package com.mall.mallapp.dto;
import com.google.gson.Gson;
/**
 * Represents a data transfer object for a shop in a mall.
 */
public class ShopDTO {

    private int floorId;
    private int mallId;
    private String shopName;
    private String desc;
    private String openingHours;

    /**
     * Default constructor for the ShopDTO class.
     */
    public ShopDTO(){};

    /**
     * Constructor for the ShopDTO class.
     *
     * @param floorId the ID of the floor where the shop is located
     * @param mallId the ID of the mall where the shop is located
     * @param shopName the name of the shop
     * @param desc the description of the shop
     * @param openingHours the opening hours of the shop
     */
    public ShopDTO(int floorId, int mallId, String shopName, String desc, String openingHours) {
        this.floorId = floorId;
        this.mallId = mallId;
        this.shopName = shopName;
        this.desc = desc;
        this.openingHours = openingHours;
    }

    /**
     * Gets the ID of the floor where the shop is located.
     *
     * @return the floor ID
     */
    public int getFloorId() {
        return floorId;
    }

    /**
     * Sets the ID of the floor where the shop is located.
     *
     * @param floorId the floor ID
     */
    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    /**
     * Gets the ID of the mall where the shop is located.
     *
     * @return the mall ID
     */
    public int getMallId() {
        return mallId;
    }

    /**
     * Sets the ID of the mall where the shop is located.
     *
     * @param mallId the mall ID
     */
    public void setMallId(int mallId) {
        this.mallId = mallId;
    }

    /**
     * Gets the name of the shop.
     *
     * @return the shop name
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Sets the name of the shop.
     *
     * @param shopName the shop name
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * Gets the description of the shop.
     *
     * @return the shop description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the description of the shop.
     *
     * @param desc the shop description
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Gets the opening hours of the shop.
     *
     * @return the shop opening hours
     */
    public String getOpeningHours() {
        return openingHours;
    }

    /**
     * Sets the opening hours of the shop.
     *
     * @param openingHours the shop opening hours
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * Converts this object to a JSON string using the Gson library.
     *
     * @return the JSON string representation of this object
     */
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Compares this object with another object for equality.
     *
     * @param obj the object to compare with this object
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ShopDTO)) {
            return false;
        }

        ShopDTO other = (ShopDTO) obj;

        boolean b = floorId == other.floorId &&
                    mallId == other.mallId &&
                shopName.equals(other.shopName) &&
                desc.equals(other.desc) &&
                openingHours.equals(other.openingHours);
        System.out.println(b);
        return b;
    }
}
