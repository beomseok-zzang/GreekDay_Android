package com.example.beomseok.greekday.model;

/**
 * Created by 김승욱 on 2016-12-16.
 */

public class OldOrder {

    public String title;
    public long timestamp;
    public boolean isAdded;
    public int price;

    public OldOrder() {
    }

    public OldOrder(String title, long timestamp, boolean isAdded, int price) {
        this.title = title;
        this.timestamp = timestamp;
        this.isAdded = isAdded;
        this.price = price;
    }

}
