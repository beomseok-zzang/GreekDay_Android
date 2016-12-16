package com.example.beomseok.greekday.util;

import com.example.beomseok.greekday.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by beomseok on 2016. 12. 8..
 */

public class FireUtils {
    static FirebaseDatabase database;
    static DatabaseReference databaseReference;
    static FirebaseUser firebaseUser;
    static String uid;

    private static FireUtils instance;
    public FireUtils(){
    }

    public static FirebaseDatabase getDatabase() {
        return database;
    }

    public static DatabaseReference getDatabaseReference() {
        return databaseReference;
    }


    public static void setUser(FirebaseUser mFirebaseUser) {

        firebaseUser = mFirebaseUser;
    }

    public static FireUtils getInstance(){
        if(instance==null){
            instance = new FireUtils();
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference();

        }
        return instance;
    }

    public static String getUid() {
        if(uid==null){
            uid=firebaseUser.getUid();
        }
        return uid;
    }
}
