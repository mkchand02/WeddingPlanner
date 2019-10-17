package com.example.myapplication.models;

public class CateringModel {
    String cuisine,image,max_guests,name,ratePerPlate;

    public CateringModel(String cuisine, String image, String max_guests, String name, String ratePerPlate) {
        this.cuisine = cuisine;
        this.image = image;
        this.max_guests = max_guests;
        this.name = name;
        this.ratePerPlate = ratePerPlate;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getImage() {
        return image;
    }

    public String getMax_guests() {
        return max_guests;
    }

    public String getName() {
        return name;
    }

    public String getRatePerPlate() {
        return ratePerPlate;
    }
}
