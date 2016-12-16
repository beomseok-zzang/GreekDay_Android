package com.example.beomseok.greekday.model;

/**
 * Created by 김승욱 on 2016-12-16.
 */

public class Notice {

    public String title;
    public long timestamp;
    public String text;
    public String imgURL;

    public Notice() {
    }

    public Notice(String title,long timestamp,String text,String imgURL) {
        this.title = title;
        this.timestamp = timestamp;
        this.text = text;
        this.imgURL = imgURL;
    }

}
