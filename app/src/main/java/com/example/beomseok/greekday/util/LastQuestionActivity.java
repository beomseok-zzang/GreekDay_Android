package com.example.beomseok.greekday.util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.beomseok.greekday.MainActivity;
import com.example.beomseok.greekday.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LastQuestionActivity extends AppCompatActivity {
    Toolbar toolbar;
    CardView cardView1;
    CardView cardView2;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_question);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("개별포장 여부");
       /* Map<String, Chat> chat = new HashMap<String, Chat>();
        chat.put(FireUtils.getUid(), new Chat(System.currentTimeMillis(),welcomeMessage));
        ref.setValue(chat);
        ref.child(FireUtils.getUid()).child("messageId").push().setValue(new Message(true,welcomeMessage,System.currentTimeMillis()));*/


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        databaseReference = FireUtils.getDatabaseReference().child("orders");
        cardView1 = (CardView) findViewById(R.id.card_not_pakaged);
        cardView2 = (CardView) findViewById(R.id.card_pakaged);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(MainActivity.CART.startOrder(false));
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(MainActivity.CART.startOrder(false));

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}
