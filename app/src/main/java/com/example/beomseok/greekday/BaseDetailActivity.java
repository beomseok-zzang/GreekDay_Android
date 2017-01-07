package com.example.beomseok.greekday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.beomseok.greekday.model.BaseItem;

public class BaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_detail);
        BaseItem baseItem = (BaseItem) getIntent().getSerializableExtra("base_object");

    }
}
