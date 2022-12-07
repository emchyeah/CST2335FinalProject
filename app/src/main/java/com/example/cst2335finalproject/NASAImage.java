package com.example.cst2335finalproject;

public class NASAImage  {

//    attributes of a NASAImage
    private String date;
    private String url;
    private String title;

//    constructor
    public NASAImage(String date, String url, String title) {
        this.date = date;
        this.url = url;
        this.title = title;
    }

//    getter methods
    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}