package com.example.beomseok.greekday.model;

import java.util.ArrayList;

/**
 * Created by beomseok on 2016. 12. 27..
 */

public class Order {
    public ArrayList<OrderYogurt> yogurts = new ArrayList<OrderYogurt>();
    public int userId;
    public int price=0;
    public String isPack;

    public Order(){
    }
    public Order( ArrayList<OrderYogurt> yogurts, int userId, int price,String isPack) {
        this.isPack=isPack;
        this.yogurts = yogurts;
        this.userId = userId;
        this.price = price;

    }
}
