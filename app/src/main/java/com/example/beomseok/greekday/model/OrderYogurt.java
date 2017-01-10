package com.example.beomseok.greekday.model;

import java.util.ArrayList;

/**
 * Created by beomseok on 2016. 12. 11..
 */

public class OrderYogurt {


    public String title;
    public int price;
    public int yogurtId;
    public String isHoneyAdded;
    public String isYogurtAdded;
    public ArrayList<Topping> toppings;
    public OrderYogurt(){

    }
    public OrderYogurt(String title, int yogurtId,  int price, String isHoneyAdded,String isYogurtAdded, ArrayList<Topping> toppings) {

        this.title = title;
        this.price = price;
        this.yogurtId = yogurtId;
        this.isHoneyAdded = isHoneyAdded;
        this.isYogurtAdded = isYogurtAdded;
        this.toppings = toppings;
    }


}
