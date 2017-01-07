package com.example.beomseok.greekday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.beomseok.greekday.adapter.NoticeRecyclerAdapter;
import com.example.beomseok.greekday.model.Notice;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("공지사항");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ArrayList<Notice> notices = new ArrayList<Notice>();
        notices.add(new Notice("안녕", System.currentTimeMillis(), "dddddddddddddddddddddddddddd", "sdsdsdsdsd"));
        notices.add(new Notice("BETA SERVICE START", System.currentTimeMillis(), "dddddddddddddddddddddddddddd", "sdsdsdsdsd"));
        NoticeRecyclerAdapter adapter = new NoticeRecyclerAdapter(notices,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}