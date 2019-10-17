package com.example.myapplication.models;

public class VenueModel {
    String address,capacity,image,name,price,rating;

    public VenueModel(String address, String capacity, String image, String name, String price, String rating) {
        this.address = address;
        this.capacity = capacity;
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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
