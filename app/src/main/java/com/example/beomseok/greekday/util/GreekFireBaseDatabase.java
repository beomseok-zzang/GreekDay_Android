package com.example.beomseok.greekday.util;

import android.util.Log;

import com.example.beomseok.greekday.model.Topping;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

/**
 * Created by beomseok on 2016. 12. 8..
 */

public class GreekFireBaseDatabase {
    FirebaseDatabase database;
    static ArrayList<Topping> arr = new ArrayList<Topping>();
    public GreekFireBaseDatabase(){
        database = FirebaseDatabase.getInstance();

        database.getReference().child("topping").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        Iterator<DataSnapshot> data =dataSnapshot.getChildren().iterator();
                        Log.d("endtime",Long.toString(System.currentTimeMillis()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // ...
                    }
                });
    }

}
