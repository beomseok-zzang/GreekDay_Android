package com.example.beomseok.greekday;

import android.support.design.widget.Snackbar;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beomseok.greekday.adapter.ChatRecyclerAdapter;
import com.example.beomseok.greekday.model.Chat;
import com.example.beomseok.greekday.model.Message;
import com.example.beomseok.greekday.util.FireUtils;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CounselActivity extends AppCompatActivity {
    public static final String CHAT_KEY = "2323";
    public static final String PARTNER_ID = "2321";
    public static final String ITEMPOST_KEY = "2320";
    public static final String ADMIN_ID = "admin";
    private ChatRecyclerAdapter adapter;
    private ChildEventListener postListener;
    ArrayList<Message> messages;
    private RecyclerView recyclerView;
    private Button btnSubmit;
    private EditText editMessage;
    private DatabaseReference ref;
    private String welcomeMessage="안녕하세요 반갑습니다. 저는 그릭데이 사장입니다. 어떤 문의사항이든 편하게 말씀해주세요";

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsel);
        //set view references
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        editMessage = (EditText) findViewById(R.id.edit_message);
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



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=editMessage.getText().toString();
                if(text==null||text.equals("")){
                    return;
                }
                Message message = new Message(false,text,System.currentTimeMillis());
                ref.child(FireUtils.getUid()).child("lastMessage").setValue(message.text);
                ref.child(FireUtils.getUid()).child("timeStamp").setValue(message.date);
                ref.child(FireUtils.getUid()).child("messageId").push().setValue(message);
                editMessage.setText("");
            }
        });
        //reference 초기화

        postListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Message message = dataSnapshot.getValue(Message.class);
                    adapter.addItem(message);
                    recyclerView.scrollToPosition(messages.size()-1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Snackbar.make(editMessage,"메시지 전송에 실패했습니다.",1500).setAction("Action", null).show();
            }
        };




    }

    @Override
    protected void onStart() {
        super.onStart();
        ref = FireUtils.getDatabaseReference().child("chat");
        //처음이라면 환영 메시지 생성
        ref.child(FireUtils.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    Map<String, Chat> chat = new HashMap<String, Chat>();
                    chat.put(FireUtils.getUid(), new Chat(System.currentTimeMillis(),welcomeMessage));
                    ref.setValue(chat);
                    ref.child(FireUtils.getUid()).child("messageId").push().setValue(new Message(true,welcomeMessage,System.currentTimeMillis()));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child(FireUtils.getUid()).child("messageId").addChildEventListener(postListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

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