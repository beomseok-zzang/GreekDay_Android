package com.example.beomseok.greekday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.beomseok.greekday.model.Yogurt;

public class BaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_detail);
        Yogurt yogurt = (Yogurt) getIntent().getSerializableExtra("base_object");

    }
}
