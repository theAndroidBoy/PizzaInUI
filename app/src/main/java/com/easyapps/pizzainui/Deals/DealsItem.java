package com.easyapps.pizzainui.Deals;

public class DealsItem {

    private String desc;
    private String price;
    private String itemName;
    private int image;

    public DealsItem(String itemName, String price, int image, String desc) {
        this.desc = desc;
        this.price = price;
        this.itemName = itemName;
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getImage() {
        return image;
    }

}
