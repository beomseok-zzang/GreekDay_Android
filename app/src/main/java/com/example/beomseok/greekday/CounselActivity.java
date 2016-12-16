package com.example.beomseok.greekday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beomseok.greekday.adapter.ChatRecyclerAdapter;
import com.example.beomseok.greekday.model.Chat;
import com.example.beomseok.greekday.model.Message;
import com.example.beomseok.greekday.util.FireUtils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CounselActivity extends AppCompatActivity {
    public static final String CHAT_KEY = "2323";
    public static final String PARTNER_ID = "2321";
    public static final String ITEMPOST_KEY = "2320";
    public static final String ADMIN_ID = "admin";
    private ChatRecyclerAdapter adapter;

    ArrayList<Message> messages;
    private RecyclerView recyclerView;

    private DatabaseReference ref;
    private String welcomeMessage="안녕하세요 반갑습니다. 저는 그릭데이 사장입니다. 어떤 문의사항이든 편하게 말씀해주세요";

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsel);

        //setting toolbar UI
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        messages = new ArrayList<Message>();

        adapter = new ChatRecyclerAdapter(messages,getLayoutInflater());
        recyclerView =(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);


        ref = FireUtils.getDatabaseReference().child("chat");
        ref.setValue(FireUtils.getUid());
        Map<String, Chat> chat = new HashMap<String, Chat>();
        chat.put(FireUtils.getUid(), new Chat(System.currentTimeMillis(),welcomeMessage));
        ref.setValue(chat);
        Map<String, Message> messageMap = new HashMap<String, Message>();
        messageMap.put("messageId", new Message(true,welcomeMessage,System.currentTimeMillis()));
        ref.child(FireUtils.getUid()).setValue(messageMap);


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Message message = dataSnapshot.getValue(Message.class);
                adapter.addItem(message);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        };
        ref.child(FireUtils.getUid()).child("messageId").addValueEventListener(postListener);



    }

    @Override
    protected void onStart() {
        super.onStart();
        //상단 라운드 아이템뷰~
/*        itemListener = new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        chatAboutItemQuery.addListenerForSingleValueEvent(itemListener);
        getQuery(mDatabase).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });*/
    }

    @Override
    protected void onStop() {
        super.onStop();
  /*      if(itemListener!=null) {
            chatAboutItemQuery.removeEventListener(itemListener);
        }*/
    }

    private Query getQuery(DatabaseReference mDatabase) {
        return mDatabase.child("chats").child(FireUtils.getUid()).child("messageId");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}