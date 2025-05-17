package com.example.myfoodapp.models;

public class CartItem {
    public String name;
    public String price;
    public String imageUrl;
    public int quantity;

    // Constructor without quantity, defaults quantity to 1
    public CartItem(String name, String price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = 1;
    }

    // New constructor with quantity
    public CartItem(String name, String price, String imageUrl, int quantity) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }
}
