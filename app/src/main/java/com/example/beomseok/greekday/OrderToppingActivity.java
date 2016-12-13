package com.example.beomseok.greekday;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.beomseok.greekday.adapter.ToppingRecyclerAdapter;
import com.example.beomseok.greekday.model.Topping;
import com.example.beomseok.greekday.util.GreekFireBaseDatabase;
import com.example.beomseok.greekday.util.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class OrderToppingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Topping> toppings;
    private HashMap<String,Topping> checkToppings;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_topping);
        //toolbar
        btnSubmit = (Button) findViewById(R.id.txt_topping);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_order);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Topping> toppings = new ArrayList<Topping>();
                Iterator<String> iter = checkToppings.keySet().iterator();
                while(iter.hasNext()){
                    toppings.add(checkToppings.get(iter.next()));
                }
                Intent intent = new Intent();
                intent.putExtra("result",toppings);
                setResult(RESULT_OK, intent);
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }
        });
        //topping 리스트 받아오기
        toppings= new ArrayList<Topping>();
        toppings.add(new Topping("1","그래놀라",300));
        toppings.add(new Topping("2","딸기",200));
        toppings.add(new Topping("3","블루베리",400));
        toppings.add(new Topping("4","망고",200));
        toppings.add(new Topping("5","호두",500));
        toppings.add(new Topping("6","뮤즐리",200));
        toppings.add(new Topping("7","바나나",500));
        toppings.add(new Topping("8","땅콩",400));
        toppings.add(new Topping("9","견과류",300));

        ///
        checkToppings = new HashMap<String,Topping>();



    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_topping);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recyclerView.setAdapter(new ToppingRecyclerAdapter(toppings, getApplicationContext(),checkToppings,getSupportActionBar()));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
    public void toppingPressed(View v) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_topping_item);
        v.startAnimation(anim);
    }
}
