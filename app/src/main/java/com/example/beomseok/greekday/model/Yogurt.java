package com.example.beomseok.greekday.model;

import android.widget.Switch;

import java.io.Serializable;

/**
 * Created by beomseok on 2016. 12. 10..
 */

public class Yogurt implements Serializable {
    public String id;
    public String name;
    public int price;
    public int size;
    public int kcal;
    public String desc;
    public String isNew;
    public String imgName;
    public String imgPath;
    public String onSale;

    public Yogurt(){

    }

    public Yogurt(String id, String name, int price, int size, int kcal, String desc, String isNew, String imgName, String imgPath, String onSale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.kcal = kcal;
        this.desc = desc;
        this.isNew = isNew;
        this.imgName = imgName;
        this.imgPath = imgPath;
        this.onSale = onSale;
    }
}
