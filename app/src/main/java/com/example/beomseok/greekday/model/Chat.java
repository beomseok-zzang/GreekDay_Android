package com.example.beomseok.greekday.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by beomseok on 2016. 12. 16..
 */

public class Chat {
    long timeStamp;
    String lastMessage;
    String messageId=null;
    Chat(){
    }

    public Chat(long timeStamp, String lastMessage) {
        this.timeStamp = timeStamp;
        this.lastMessage = lastMessage;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("timeStamp",timeStamp);
        result.put("lastMessage",lastMessage);
        return result;
    }
}

