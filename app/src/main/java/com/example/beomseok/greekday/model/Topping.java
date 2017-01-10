package com.example.beomseok.greekday.model;

import java.io.Serializable;

/**
 * Created by beomseok on 2016. 12. 7..
 */

public class Topping implements Serializable {
    public int id;
    public String dateTime;
    public String isNew;
    public String name;
    public int price;
    public int kcal;
    public String desc;
    public String imgName;
    public String imgPath;
    public String onSale;


    public Topping() {

    }

    public Topping(int id, String dateTime, String isNew, String name, int price, int kcal, String desc, String imgName, String imgPath, String onSale) {
        this.id = id;
        this.dateTime = dateTime;
        this.isNew = isNew;
        this.name = name;
        this.price = price;
        this.kcal = kcal;
        this.desc = desc;
        this.imgName = imgName;
        this.imgPath = imgPath;
        this.onSale = onSale;
    }
}
