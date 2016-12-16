package com.example.beomseok.greekday.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by beomseok on 2016. 12. 15..
 */
public class Message {

    public boolean isOwner;
    public String text;
    public long date;
    public Message( boolean isOwner,String text,long date) {
        this.isOwner = isOwner;
        this.text = text;
        this.date = date;
    }
    Message(){

    }
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("receiverId",isOwner);
        result.put("text",text);
        result.put("date",date);
        return result;
    }
}