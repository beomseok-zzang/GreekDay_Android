package com.example.beomseok.greekday.model;

import java.util.ArrayList;

/**
 * Created by beomseok on 2016. 12. 14..
 */

public class Cart {
    private String id;
    private  ArrayList<Yogurt> yogurts = new ArrayList<Yogurt>();
    private String userId;
    private int price=0;
    private long timeStamp;

    public void Cart(){

    }
    public void addCart(Yogurt yogurt){
        this.yogurts.add(yogurt);
    }
    public void removeCart(Yogurt yogurt){
        this.yogurts.remove(yogurt);
    }
    public int getYogurtSize(){
        return yogurts.size();
    }
    public ArrayList<Yogurt> getYogurts(){
        return yogurts;
    }
    public Cart startOrder(String id){
        //userId == firebasekey
        timeStamp = System.currentTimeMillis();
        this.price = 0;
        for(Yogurt yogurt:this.yogurts){
            this.price +=yogurt.price;
        }

        return this;
    }
}
