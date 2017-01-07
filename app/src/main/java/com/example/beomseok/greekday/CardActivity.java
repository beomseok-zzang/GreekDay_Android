package com.example.beomseok.greekday;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.beomseok.greekday.adapter.UsedOrderRecyclerAdapter;
import com.example.beomseok.greekday.model.OldOrder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {
    ImageView ivBarcord;
    RecyclerView recyclerView;
    Toolbar toolbar;
    UsedOrderRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ivBarcord = (ImageView) findViewById(R.id.iv_barcode);
                recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //What to do on back clicked
                        onBackPressed();
                    }
                });
                getSupportActionBar().setTitle("카드");

                ArrayList<OldOrder> oldOrders = new ArrayList<OldOrder>();
                //String title, long timestamp, String isAdded, int price
                oldOrders.add(new OldOrder("yaya", System.currentTimeMillis(), false, 3000));
                oldOrders.add(new OldOrder("yaya", System.currentTimeMillis(), false, 5000));
                oldOrders.add(new OldOrder("yaya", System.currentTimeMillis(), false, 7000));
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new UsedOrderRecyclerAdapter(oldOrders);
                recyclerView.setAdapter(adapter);
                String strBarcode = "greekday";
                Bitmap barcode = createBarcode(strBarcode);
                ivBarcord.setImageBitmap(barcode);
                ivBarcord.invalidate();
            }

            public Bitmap createBarcode(String code) {


                Bitmap bitmap = null;
                MultiFormatWriter gen = new MultiFormatWriter();
                try {
                    final int WIDTH = 500;
                    final int HEIGHT = 160;


                    BitMatrix bytemap = gen.encode(code, BarcodeFormat.PDF_417, WIDTH, HEIGHT);
                    bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
                    for (int i = 0; i < WIDTH; ++i)
                        for (int j = 0; j < HEIGHT; ++j) {
                            bitmap.setPixel(i, j, bytemap.get(i, j) ? Color.BLACK : Color.WHITE);
                        }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return bitmap;
            }


            @Override
            public void onBackPressed() {
                super.onBackPressed();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            }
        }
