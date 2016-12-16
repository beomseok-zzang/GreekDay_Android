package com.example.beomseok.greekday.model;

import java.util.ArrayList;

/**
 * Created by beomseok on 2016. 12. 11..
 */

public class Yogurt {
    public String id;
    public char size;
    public String title;
    public int price;
    public boolean honey;
    public boolean isSizeUp;
    public ArrayList<Topping> toppings;
    public Yogurt(){

    }
    public Yogurt(String title, char size,  int price, boolean honey,boolean isSizeUp, ArrayList<Topping> toppings) {
        this.size = size;
        this.title = title;
        this.price = price;
        this.honey = honey;
        this.isSizeUp = isSizeUp;
        this.toppings = toppings;
    }
    public Yogurt(String id, String title, char size,  int price, boolean honey,boolean isSizeUp, ArrayList<Topping> toppings) {
        this.id = id;
        this.size = size;
        this.title = title;
        this.price = price;
        this.honey = honey;
        this.isSizeUp = isSizeUp;
        this.toppings = toppings;
    }

}
