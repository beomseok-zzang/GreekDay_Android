package com.example.beomseok.greekday.model;

import java.util.ArrayList;

/**
 * Created by beomseok on 2016. 12. 27..
 */

public class Order {
    public ArrayList<Yogurt> yogurts = new ArrayList<Yogurt>();
    public String userId;
    public int price=0;
    public long timeStamp;
    public boolean isPackaged;

    public Order(){
    }
    public Order( ArrayList<Yogurt> yogurts, String userId, int price, long timeStamp,boolean isPackaged) {
        this.isPackaged=isPackaged;
        this.yogurts = yogurts;
        this.userId = userId;
        this.price = price;
        this.timeStamp = timeStamp;
    }
}
