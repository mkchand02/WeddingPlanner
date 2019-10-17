package com.example.myapplication.models;

public class DecorationModel {
    String address,image, name, price, rating;

    public DecorationModel(String address, String image, String name, String price, String rating) {
        this.address = address;
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
