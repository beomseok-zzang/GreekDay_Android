package com.example.beomseok.greekday.model;

import java.io.Serializable;

/**
 * Created by beomseok on 2017. 1. 7..
 */

public class User implements Serializable{
    public String email;
    public String nickName;
    public String phonNumber;
    public User(String email, String nickName,String phonNumber){
        this.email  =email;
        this.nickName = nickName;
        this.phonNumber = phonNumber;
    }
    public User(String email){
        this.email  =email;

    }
}
