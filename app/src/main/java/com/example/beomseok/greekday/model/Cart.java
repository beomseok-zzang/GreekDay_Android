package com.example.beomseok.greekday.model;

import com.example.beomseok.greekday.util.FireUtils;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by beomseok on 2016. 12. 14..
 */

public class Cart extends Observable {

    private  ArrayList<Yogurt> yogurts = new ArrayList<Yogurt>();
    private String userId;
    private int price=0;
    private long timeStamp;

    public void Cart(){
    }
    public void addYogurt(Yogurt yogurt){
        this.yogurts.add(yogurt);
        this.action();
    }
    public void removeYogurt(int position){
        this.yogurts.remove(position);
        this.action();
    }
    public int getYogurtSize(){
        return yogurts.size();
    }
    public ArrayList<Yogurt> getYogurts(){
        return yogurts;
    }
    public Order startOrder(boolean isPackaged){
        timeStamp = System.currentTimeMillis();
        userId = FireUtils.getUid();
        this.price = 0;
        for(Yogurt yogurt:this.yogurts){
            this.price +=yogurt.price;
        }
        return new Order(yogurts,userId,price,timeStamp,isPackaged);
    }
    public void action() {
        setChanged();
        notifyObservers();
    }
}
