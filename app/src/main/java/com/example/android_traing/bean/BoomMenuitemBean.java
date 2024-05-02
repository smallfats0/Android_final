package com.example.android_traing.bean;

public class BoomMenuitemBean {
    private String title;
    private int imageid;

    public BoomMenuitemBean(String title, int imageid) {
        this.title = title;
        this.imageid = imageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}
