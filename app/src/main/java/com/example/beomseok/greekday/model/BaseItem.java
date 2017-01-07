package com.example.beomseok.greekday.model;

import android.widget.Switch;

import java.io.Serializable;

/**
 * Created by beomseok on 2016. 12. 10..
 */

public class BaseItem implements Serializable {
    public String id;
    public String title;
    public static final char  SIZE_SMALL='S';
    public static final char  SIZE_MEDIUM='M';
    public static final char  SIZE_LARGE='L';
    public char size;
    public int price;
    public BaseItem(){

    }
    public BaseItem(String id,String title,int price,char size){
        this.id = id;
        this.title = title;
        this.price= price;
        this.size = size;
    }
    public BaseItem(String title,int price,char size){
        this.title = title;
        this.price = price;
        this.size = size;
    }

}
