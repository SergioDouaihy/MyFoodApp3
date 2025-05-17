package com.example.myfoodapp.models;

import com.example.myfoodapp.R;

public class MenuItemModel {
    private String id; // Firestore document ID
    private String name;
    private String imageUrl;
    private String price;
    private String position;

    // Constructor with id (for creating new items from Firestore data)
    public MenuItemModel(String id, String name, String imageUrl, String price, String position) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.position = position;
    }

    // Constructor without id (for updating existing items)
    public MenuItemModel(String name, String imageUrl, String price, String position) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.position = position;
    }


    // Getters
    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPrice() {
        return price;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    // Add this method for image resource ID
    public int getImageResId() {
        // You can return a drawable resource id here based on the imageUrl
        // For example, you can return a default image if the imageUrl is empty or invalid:
        if (imageUrl == null || imageUrl.isEmpty()) {
            return R.drawable.placeholder; // A default placeholder
        }
        // If you are using URLs for images, you can load them with a library like Picasso or Glide.
        return 0; // For example, return a default image resource
    }
}