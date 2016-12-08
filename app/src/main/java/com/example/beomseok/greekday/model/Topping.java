package com.example.beomseok.greekday.model;

import java.io.Serializable;

/**
 * Created by beomseok on 2016. 12. 7..
 */

public class Topping implements Serializable {
    public String name;
    public String id;
    public int price;
    public Topping(String id,String name,int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
