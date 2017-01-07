package com.example.beomseok.greekday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.beomseok.greekday.adapter.NoticeRecyclerAdapter;
import com.example.beomseok.greekday.model.Notice;
import com.example.beomseok.greekday.model.OldOrder;
import com.example.beomseok.greekday.model.Topping;

import java.io.Serializable;
import java.util.ArrayList;

public class NoticeDetailActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private Notice notice;
    private TextView tvTitle;
    private TextView tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        notice = (Notice) getIntent().getSerializableExtra("notice_data");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(notice.title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(notice.title);
        tvDescription.setText(notice.text);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}