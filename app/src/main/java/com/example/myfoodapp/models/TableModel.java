package com.example.myfoodapp.models;

public class TableModel {
    private String tableNumber;
    private boolean isOccupied;

    // Required empty constructor for Firestore deserialization
    public TableModel() {}

    public TableModel(String tableNumber, boolean isOccupied) {
        this.tableNumber = tableNumber;
        this.isOccupied = isOccupied;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}