package com.example.beomseok.greekday.util;

import com.example.beomseok.greekday.model.Topping;
import com.example.beomseok.greekday.model.Yogurt;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by beomseok on 2017. 1. 7..
 */

public class GLOBAL {
    public static int userId;
    public static final String api="http://52.78.247.195:8080/Greekday/android";
    public static Map<String,Topping> toppings;
    public static Map<String,Yogurt> yogurts;

    public static String makeBooleanToString(boolean input){
        if(input){
            return "T";
        }else{
            return "F";
        }
    }
    // TODO: 2017. 1. 7.
    public static Topping getTopping(String id){
        return toppings.get(id);
    }
    // TODO
    public static Yogurt getYogurt(String id){
        return yogurts.get(id);
    }
}
