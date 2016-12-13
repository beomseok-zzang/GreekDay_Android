package com.example.beomseok.greekday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.beomseok.greekday.adapter.OrderBaseRecyclerAdapter;
import com.example.beomseok.greekday.model.BaseItem;
import com.example.beomseok.greekday.model.Topping;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderBaseActivity extends AppCompatActivity {
    public static final int REQUEST_CODE=3312;
    RecyclerView recyclerView;
    ArrayList<BaseItem> baseItems = new ArrayList<BaseItem>();
    OrderBaseRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_base);
        baseItems.add(new BaseItem("1","플레인",3000,5000,6000));
        baseItems.add(new BaseItem("2","그래놀라 바나나",3000,5000,6000));
        baseItems.add(new BaseItem("3","그래놀라 블루베리",3000,5000,6000));
        baseItems.add(new BaseItem("4","그래놀라 딸기",3000,5000,6000));
        baseItems.add(new BaseItem("5","생과일믹스",3000,5000,6000));
        baseItems.add(new BaseItem("6","그래놀라 제철과일",3000,5000,6000));
        baseItems.add(new BaseItem("7","블루베리 호두",3000,5000,6000));
        baseItems.add(new BaseItem("8","무화과 청포도",3000,5000,6000));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
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


        adapter = new OrderBaseRecyclerAdapter(baseItems,this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_base);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.order_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){

            Serializable list=data.getSerializableExtra("result");

            //경고 메세지를 없에주는 어노테이션
            @SuppressWarnings("unchecked")
            ArrayList<Topping> l = (ArrayList<Topping>)list;
            adapter.setSelectedToppongs(l);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}
