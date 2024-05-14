package com.example.android_cjj118.bean;

public class Course {
    private int imageId;
    private String name;
    private float rating;

    public Course(int imageId, String name, float rating) {
        this.imageId = imageId;
        this.name = name;
        this.rating = rating;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
