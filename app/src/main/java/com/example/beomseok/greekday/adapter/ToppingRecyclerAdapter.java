package com.example.beomseok.greekday.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beomseok.greekday.R;
import com.example.beomseok.greekday.model.Topping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by beomseok on 2016. 12. 7..
 */

public class ToppingRecyclerAdapter extends RecyclerView.Adapter<ToppingRecyclerAdapter.ViewHolder> {
    private ArrayList<Topping> toppings;
    private HashMap<String,Topping>  checkedToppings;
    private ActionBar actionBar;
    private Context context;
    private int dummyImg[] = {R.mipmap.sample_1,R.mipmap.sample_2,R.mipmap.sample_3,R.mipmap.sample_4,R.mipmap.sample_5
            ,R.mipmap.sample_6,R.mipmap.sample_7,R.mipmap.sample_8};
    public ToppingRecyclerAdapter(ArrayList<Topping> toppings, Context context, HashMap<String,Topping> checkedToppings, ActionBar actionBar){
        if (toppings == null) {
            throw new IllegalArgumentException(
                    "modelData must not be null");
        }
        this.checkedToppings = checkedToppings;
        this.actionBar = actionBar;
        this.context = context;
        this.toppings = toppings;

    }

    /**
     * Interface for receiving click events from cells.
     */

    /**
     * Custom viewholder for our planet views.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder  {
        public final TextView tvToppingName;
        public final TextView tvToppingPrice;
        public final ImageView ivTopping;
        public final ImageView ivClick;
        public final CheckBox checkBox;
        public final LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_order);
            tvToppingName = (TextView) itemView.findViewById(R.id.txt_item_topping_name);
            tvToppingPrice = (TextView) itemView.findViewById(R.id.txt_item_topping_price);
            ivTopping = (ImageView) itemView.findViewById(R.id.img_item_topping);
            ivClick = (ImageView) itemView.findViewById(R.id.iv_order_click);
            checkBox = (CheckBox) itemView.findViewById(R.id.order_check_box);
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.recycler_topping_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ViewHolder tpHoder = holder;
        tpHoder.tvToppingName.setText(toppings.get(position).name);
        tpHoder.tvToppingPrice.setText(""+toppings.get(position).price);
        tpHoder.ivTopping.setImageResource(dummyImg[position%8]);
        tpHoder.ivClick.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.anim_topping_item);
                tpHoder.ivTopping.startAnimation(anim);
                //is not checked
                if(!tpHoder.checkBox.isChecked()){
                    tpHoder.checkBox.setChecked(true);

                    //checkedToppings.put(toppings.get(position).id, toppings.get(position));
                    if(actionBar.getTitle()==null) {
                        actionBar.setTitle(toppings.get(position).name);
                    }else
                        actionBar.setTitle(actionBar.getTitle() + " " + toppings.get(position).name);


                    //is checked
                }else{

                    tpHoder.checkBox.setChecked(false);
                    String result="";
                    checkedToppings.remove(toppings.get(position).id);
                    Iterator<String> iter = checkedToppings.keySet().iterator();
                    while(iter.hasNext()){
                        result = result+checkedToppings.get(iter.next()).name+"  ";
                    }
                    actionBar.setTitle(result);
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return toppings.size();
    }
}