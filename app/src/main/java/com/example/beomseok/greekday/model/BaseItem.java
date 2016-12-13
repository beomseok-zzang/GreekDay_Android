package com.example.beomseok.greekday.model;

import android.widget.Switch;

/**
 * Created by beomseok on 2016. 12. 10..
 */

public class BaseItem {
    public String id;
    public String title;
    public static final int  SIZE_SMALL=1;
    public static final int  SIZE_MEDIUM=2;
    public static final int  SIZE_LARGE=3;
    public int priceSmall;
    public int priceMedium;
    public int priceLarge;
    public BaseItem(){

    }
    public BaseItem(String id,String title,int priceSmall,int priceMedium,int priceLarge){
        this.id = id;
        this.title = title;
        this.priceSmall = priceSmall;
        this.priceMedium = priceMedium;
        this.priceLarge = priceLarge;
    }
    public BaseItem(String title,int priceSmall,int priceMedium,int priceLarge){
        this.title = title;
        this.priceSmall = priceSmall;
        this.priceMedium = priceMedium;
        this.priceLarge = priceLarge;
    }
    public int getPrice(int size){
        switch (size){
            case SIZE_SMALL:
                return priceSmall;
            case SIZE_MEDIUM:
                return priceMedium;
            case SIZE_LARGE:
                return priceLarge;
        }
        return 0;
    }
}
