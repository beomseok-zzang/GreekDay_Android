package com.example.beomseok.greekday.model;

/**
 * Created by beomseok on 2017. 1. 7..
 */

public class Branch {
    public Branch(){}
    public int id;
    public String name;
    public int stateId;

    public Branch(int id, String name, int stateId) {
        this.id = id;
        this.name = name;
        this.stateId = stateId;
    }
}
