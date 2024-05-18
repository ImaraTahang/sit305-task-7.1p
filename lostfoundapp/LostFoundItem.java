package com.example.lostfoundapp;

public class LostFoundItem {
    private String postType;
    private String name;
    private String phone;
    private String description;
    private String date;
    private String location;

    public LostFoundItem(String postType, String name, String phone, String description, String date, String location){
        this.postType=postType;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
    }
    public String getPostType() {
        return postType;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
