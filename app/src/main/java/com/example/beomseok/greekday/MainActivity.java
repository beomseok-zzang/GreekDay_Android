package com.example.beomseok.greekday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.layout_card).setOnClickListener(this);
        findViewById(R.id.layout_counsel).setOnClickListener(this);
        findViewById(R.id.layout_order).setOnClickListener(this);
        findViewById(R.id.layout_notice).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout_card:
                startActivity(new Intent(this, CardActivity.class));
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
            case R.id.layout_counsel:
                startActivity(new Intent(this, CounselActivity.class));
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
            case R.id.layout_order:
                startActivity(new Intent(this, OrderActivity.class));
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
            case R.id.layout_notice:
                startActivity(new Intent(this, NoticeActivity.class));
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                break;
        }



    }
}
