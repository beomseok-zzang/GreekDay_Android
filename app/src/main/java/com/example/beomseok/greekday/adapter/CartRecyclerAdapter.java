package com.example.beomseok.greekday.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beomseok.greekday.MainActivity;
import com.example.beomseok.greekday.R;
import com.example.beomseok.greekday.model.Cart;
import com.example.beomseok.greekday.model.OrderYogurt;
import com.example.beomseok.greekday.model.Topping;
import com.example.beomseok.greekday.model.Yogurt;
import com.example.beomseok.greekday.util.GLOBAL;

import java.util.ArrayList;

/**
 * Created by beomseok on 2016. 12. 14..
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder> {

    private ArrayList<OrderYogurt> orderYogurts;
    private Context context;

    public CartRecyclerAdapter(Cart cart, Context context) {

        this.context = context;
    }

    @Override
    public CartRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.recycler_cart_item, parent, false);

        return new CartRecyclerAdapter.ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvTitle;
        public final TextView tvPrice;
        public final TextView tvDetailInfo;
        public final TextView tvToppings;


        public ViewHolder(final View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvDetailInfo = (TextView) itemView.findViewById(R.id.tv_detail_info);
            tvToppings = (TextView) itemView.findViewById(R.id.tv_added_topping);
        }

    }

    public void removeItem(int position) {
        MainActivity.CART.removeYogurt(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, orderYogurts.size());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ViewHolder cHolder = holder;
        //cHolder.tvTitle.setText(Integer.toString(position + 1) + ". " + orderYogurts.get(position));
        cHolder.tvPrice.setText(Integer.toString(orderYogurts.get(position).price) + " 원");
        //cHolder.tvDetailInfo.setText(getDetailStr(orderYogurts.get(position).));
        //cHolder.tvToppings.setText(getToppingStr(orderYogurts.get(position)));
    }

    private String getDetailStr(OrderYogurt orderYogurt) {
        StringBuilder result = new StringBuilder();
        //if (GLOBAL.makeBooleanToString(orderYogurt.isYogurtAdded)) {
        //    result.append("요거트 ");
        //}
        //if (orderYogurt.isHoneyAdded) {
         //   result.append("꿀 ");
        //}
        return result.toString();
    }

    private String getToppingStr(OrderYogurt orderYogurt) {
        StringBuilder result = new StringBuilder();
        if (orderYogurt.toppings.size() < 1)
            return "";
        else
            result.append("추가토핑: ");/*
        for (Topping topping : orderYogurts.toppings) {
            result.append(topping.name + " ");
        }*/

        return result.toString();
    }


    @Override
    public int getItemCount() {
        return orderYogurts.size();
    }
}
