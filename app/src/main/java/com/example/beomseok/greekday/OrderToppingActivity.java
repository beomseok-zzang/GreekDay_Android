package com.example.beomseok.greekday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.beomseok.greekday.adapter.ToppingRecyclerAdapter;
import com.example.beomseok.greekday.model.Topping;
import com.example.beomseok.greekday.util.NetWorkListener;
import com.example.beomseok.greekday.util.NetworkTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OrderToppingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Topping> toppings;
    ToppingRecyclerAdapter toppingRecyclerAdapter;
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
        if(toppings.isEmpty()){

            NetworkTask networkTask = new NetworkTask(new NetWorkListener() {
                @Override
                public void postSucceed(Object object) {

                    Set<Topping> set= ((Map)object).entrySet();
                    Iterator<Topping> it = set.iterator();
                    while(it.hasNext()){
                        toppings.add(it.next());
                        toppingRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            });
            networkTask.execute(NetworkTask.TOPPPING);
        }
        ///
        toppingRecyclerAdapter = new ToppingRecyclerAdapter(toppings, getApplicationContext(),checkToppings,getSupportActionBar());
        checkToppings = new HashMap<String,Topping>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_topping);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recyclerView.setAdapter(toppingRecyclerAdapter);


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
