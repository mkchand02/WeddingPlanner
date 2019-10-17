package com.example.myapplication.models;

public class UserModel {

    public String name, email, gender, type;//type is bride or groom
    public String photo,phone;
    public int status1,status2,status3,status4,status5,status6;

    void UserModel(){}

    public UserModel(String name, String email, String gender, String type, String phone) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.photo = "https://firebasestorage.googleapis.com/v0/b/wedding-planner-shubhmangalam.appspot.com/o/profile_image_placeholder.png?alt=media&token=e925b42a-606d-40c3-b538-ae7abf18ed1a";
        this.type = type;
        this.phone = phone;
        this.status1=0;
        this.status2=0;
        this.status3=0;
        this.status4=0;
        this.status5=0;
        this.status6=0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus1(int status1) {
        this.status1 = status1;
    }

    public void setStatus2(int status2) {
        this.status2 = status2;
    }

    public void setStatus3(int status3) {
        this.status3 = status3;
    }

    public void setStatus4(int status4) {
        this.status4 = status4;
    }

    public void setStatus5(int status5) {
        this.status5 = status5;
    }

    public void setStatus6(int status6) {
        this.status6 = status6;
    }
}
