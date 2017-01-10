package com.example.beomseok.greekday.model;

import java.io.Serializable;

/**
 * Created by beomseok on 2017. 1. 7..
 */

public class Code implements Serializable {
    public int id;
    public String categoryName;
    public String categoryDesc;
    public String name;
    public String desc;
    Code(){}

    public Code(int id, String categoryName, String categoryDesc, String name, String desc) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.name = name;
        this.desc = desc;
    }
}
