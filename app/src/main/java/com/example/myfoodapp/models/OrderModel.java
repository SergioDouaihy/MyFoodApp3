package com.example.myfoodapp.models;

public class OrderModel {


    private String tableNumber;
    private String items;
    private String status;
    private double totalPrice;

    public OrderModel( String tableNumber, String items, String status, double totalPrice) {


        this.tableNumber = tableNumber;
        this.items = items;
        this.status = status;
        this.totalPrice = totalPrice;
    }


    public String getTableNumber() { return tableNumber; }
    public String getItems() { return items; }
    public String getStatus() { return status; }
    public double getTotalPrice() { return totalPrice; }
}