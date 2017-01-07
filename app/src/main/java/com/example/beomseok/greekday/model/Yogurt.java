package com.example.beomseok.greekday.model;

import java.util.ArrayList;

/**
 * Created by beomseok on 2016. 12. 11..
 */

public class Yogurt {
    public String id;
    public String size;
    public String title;
    public int price;
    public boolean isHoneyAdded;
    public boolean isYogurtAdded;
    public ArrayList<Topping> toppings;
    public Yogurt(){

    }
    public Yogurt(String title, char size,  int price, boolean isHoneyAdded,boolean isYogurtAdded, ArrayList<Topping> toppings) {
        this.size = Character.toString(size);
        this.title = title;
        this.price = price;
        this.isHoneyAdded = isHoneyAdded;
        this.isYogurtAdded = isYogurtAdded;
        this.toppings = toppings;
    }
    public Yogurt(String id, String title, char size,  int price, boolean isHoneyAdded,boolean isYogurtAdded, ArrayList<Topping> toppings) {
        this.id = id;
        this.size = Character.toString(size);
        this.title = title;
        this.price = price;
        this.isHoneyAdded = isHoneyAdded;
        this.isYogurtAdded = isYogurtAdded;
        this.toppings = toppings;
    }

}
