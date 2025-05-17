package com.example.myfoodapp.utils;

import com.example.myfoodapp.models.CartModel;

import java.util.ArrayList;

public class CartData {
    public static ArrayList<CartModel> cartItemList = new ArrayList<>();

    public static void addToCart(CartModel item) {
        cartItemList.add(item);
    }
}
