package com.example.myfoodapp.models;

public class HomeVerModel {
    String image;
    String name, rating, timing;
    String price;

    public HomeVerModel(String image, String name, String rating, String timing, String price) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.timing = timing;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getTiming() {
        return timing;
    }

    public String getPrice() {
        return price;
    }
}
