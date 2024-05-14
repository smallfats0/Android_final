package com.example.android_cjj118.ui.me.music;

import android.net.Uri;

public class Song {
    private String name;
    private String author;
    private int imageId;
    private Uri uri;

    public Song(String name, String author, int imageId, int rawId) {
        this.name = name;
        this.author = author;
        this.imageId = imageId;
        this.uri = Uri.parse("android.resource://com.example.android_cjj118.ui.music/" + rawId);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public Uri getUri() {
        return uri;
    }
    public void setUri(Uri uri) {
        this.uri = uri;
    }
}