package com.example.beomseok.greekday.model;

import com.example.beomseok.greekday.util.GLOBAL;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by beomseok on 2016. 12. 14..
 */

public class Cart extends Observable {

    private  ArrayList<OrderYogurt> orderYogurts = new ArrayList<OrderYogurt>();
    private int userId;
    private int price=0;
    private long timeStamp;

    public void Cart(){
    }
    public void addYogurt(OrderYogurt orderYogurt){
        this.orderYogurts.add(orderYogurt);
        this.action();
    }
    public void removeYogurt(int position){
        this.orderYogurts.remove(position);
        this.action();
    }
    public int getYogurtSize(){
        return orderYogurts.size();
    }
    public ArrayList<OrderYogurt> getOrderYogurts(){
        return orderYogurts;
    }

    public Order startOrder(boolean isPackaged){
        timeStamp = System.currentTimeMillis();
        userId = GLOBAL.userId;
        this.price = 0;
        for(OrderYogurt orderYogurt:this.orderYogurts){
            this.price +=orderYogurt.price;
        }
        //ArrayList<OrderYogurt> yogurts, int userId, int price,String isPack
        return new Order(orderYogurts,userId,price, GLOBAL.makeBooleanToString(isPackaged));
    }
    public void action() {
        setChanged();
        notifyObservers();
    }
}
