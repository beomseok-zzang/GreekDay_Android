package com.example.beomseok.greekday;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beomseok.greekday.adapter.CartRecyclerAdapter;
import com.example.beomseok.greekday.model.Yogurt;

public class CartActivity extends AppCompatActivity {

    CartRecyclerAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    private Paint p;
    TextView tvTotalPrice;
    TextView tvBoughtNumber;
    Button btnBuy;
    Bitmap icon;
    int totalPrice=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        p = new Paint();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvBoughtNumber = (TextView) findViewById(R.id.tv_bought_number);
        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LastQuestionActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("장바구니");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
        adapter = new CartRecyclerAdapter(MainActivity.CART,getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        initSwipe();

    }


    private void setTextViews(){
        totalPrice = 0;
        tvBoughtNumber.setText("총 "+Integer.toString(MainActivity.CART.getYogurtSize())+"개");
        for(Yogurt yogurt:MainActivity.CART.getYogurts()){
            totalPrice+=yogurt.price;
        }
        tvTotalPrice.setText(Integer.toString(totalPrice)+"원");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //notice is empty cart
        if(MainActivity.CART.getYogurtSize()<1)
            findViewById(R.id.layout_notice_empty).setVisibility(View.VISIBLE);
        else {
            findViewById(R.id.layout_notice_empty).setVisibility(View.GONE);
            setTextViews();
        }

    }

    private void initSwipe(){

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    adapter.removeItem(position);
                    setTextViews();
                }else {

                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {


                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;


                    float height = ((float) itemView.getBottom() - (float) itemView.getTop());
                    float width = (height / 3) ;


                    if(dX < 0){

                        icon = getBitmapFromVectorDrawable(R.drawable.ic_delete_white_24dp);
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private Bitmap getBitmapFromVectorDrawable(int drawableId){
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(getApplicationContext(), drawableId);
        try {
            Bitmap bitmap;

            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            // Handle the error
            return null;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}
