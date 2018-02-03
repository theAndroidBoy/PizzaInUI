package com.easyapps.pizzainui;


public class OrderDetail {
    public int price;
    public String itemName;

    public OrderDetail(int price, String itemName) {
        this.price = price;
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }
}// OrderDetail class closed
