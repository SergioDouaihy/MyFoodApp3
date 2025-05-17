package com.example.myfoodapp.models;

import java.util.List;

public class OrderData {
    public String tableNumber;
    public double totalPrice;
    public List<Object> cartItems;

    public OrderData() {} // Needed for Firestore

    public OrderData(String tableNumber, double totalPrice, List<Object> cartItems) {
        this.tableNumber = tableNumber;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }
}
