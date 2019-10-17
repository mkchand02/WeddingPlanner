package com.example.myapplication.models;

public class ClothingModel {
    String color,design,image,name,price;

    public ClothingModel(String color, String design, String image, String name, String price) {
        this.color = color;
        this.design = design;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDesign(String design) {
        this.design = design;
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
}
