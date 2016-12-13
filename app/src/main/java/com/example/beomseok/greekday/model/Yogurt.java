package com.example.beomseok.greekday.model;

import java.util.ArrayList;

/**
 * Created by beomseok on 2016. 12. 11..
 */

public class Yogurt {
    String id;
    int size;
    String title;
    int price;
    boolean honey;
    ArrayList<Topping> toppings;
    public Yogurt(){

    }
    public Yogurt(String title, int size,  int price, boolean honey, ArrayList<Topping> toppings) {
        this.size = size;
        this.title = title;
        this.price = price;
        this.honey = honey;
        this.toppings = toppings;
    }
    public Yogurt(String id, String title, int size,  int price, boolean honey, ArrayList<Topping> toppings) {
        this.id = id;
        this.size = size;
        this.title = title;
        this.price = price;
        this.honey = honey;
        this.toppings = toppings;
    }

}
