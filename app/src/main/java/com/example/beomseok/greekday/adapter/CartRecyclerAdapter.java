package com.example.beomseok.greekday.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beomseok.greekday.MainActivity;
import com.example.beomseok.greekday.R;
import com.example.beomseok.greekday.model.Cart;
import com.example.beomseok.greekday.model.Topping;
import com.example.beomseok.greekday.model.Yogurt;

import java.util.ArrayList;

/**
 * Created by beomseok on 2016. 12. 14..
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder> {

    private ArrayList<Yogurt> yogurts;
    private Context context;

    public CartRecyclerAdapter(Cart cart, Context context) {
        this.yogurts = cart.getYogurts();
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
        yogurts.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, yogurts.size());
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ViewHolder cHolder = holder;
        cHolder.tvTitle.setText(yogurts.get(position).title);
        cHolder.tvPrice.setText(Integer.toString(yogurts.get(position).price));
        cHolder.tvDetailInfo.setText(getDetailStr(yogurts.get(position)));
        cHolder.tvToppings.setText(getToppingStr(yogurts.get(position)));
    }
    private String getDetailStr(Yogurt yogurt){
        StringBuilder result = new StringBuilder();
        if(yogurt.isSizeUp){
            result.append("요거트 추가 ");
        }
        if(yogurt.honey){
            result.append("꿀 추가");
        }
        return result.toString();
    }
    private String getToppingStr(Yogurt yogurt){
        StringBuilder result = new StringBuilder();
        for(Topping topping:yogurt.toppings){
            result.append(topping.name+" ");
        }
        return result.toString();
    }




    @Override
    public int getItemCount() {
        return yogurts.size();
    }
}
