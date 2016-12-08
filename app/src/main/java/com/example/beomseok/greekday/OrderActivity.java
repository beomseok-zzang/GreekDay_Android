package com.example.beomseok.greekday;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.beomseok.greekday.adapter.ToppingRecyclerAdapter;
import com.example.beomseok.greekday.model.Topping;
import com.example.beomseok.greekday.util.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Topping> toppings;
    private HashMap<String,Topping> checkToppings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_order);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();

            }
        });
        //topping 리스트 받아오기
        toppings= new ArrayList<Topping>();
        toppings.add(new Topping("1","그래놀라",3000));
        toppings.add(new Topping("2","그래놀라",3000));
        toppings.add(new Topping("3","그래놀라",3000));
        toppings.add(new Topping("4","그래놀라",3000));
        toppings.add(new Topping("5","그래놀라",3000));
        toppings.add(new Topping("6","그래놀라",3000));
        toppings.add(new Topping("7","그래놀라",3000));
        toppings.add(new Topping("8","그래놀라",3000));
        toppings.add(new Topping("9","그래놀라",3000));

        ///
        checkToppings = new HashMap<String,Topping>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_topping);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        recyclerView.setAdapter(new ToppingRecyclerAdapter(toppings, getApplicationContext(),checkToppings,(TextView) findViewById(R.id.txt_topping)));



    }


    private class OnItemClickListener extends RecyclerItemClickListener.SimpleOnItemClickListener {


        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onItemClick(View childView, int position) {
            // Do something when an item is clicked, or override something else.

            //childView.findViewById(R.id.iv_ripple).performClick();
            Log.d("dddd","ddd");
            childView.findViewById(R.id.frame_order).setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}
