package com.example.beomseok.greekday.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beomseok.greekday.R;
import com.example.beomseok.greekday.model.Notice;
import com.example.beomseok.greekday.model.OldOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 김승욱 on 2016-12-16.
 */

public class UsedOrderRecyclerAdapter extends RecyclerView.Adapter<UsedOrderRecyclerAdapter.ViewHolder> {
    SimpleDateFormat dayTime;
    ArrayList<OldOrder> oldOrders;
    public UsedOrderRecyclerAdapter(ArrayList<OldOrder> oldOrders){
        dayTime = new SimpleDateFormat("yyyy년 MM월 dd일");
        this.oldOrders = oldOrders;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvTimestamp;
        TextView tvPrice;

        public ViewHolder(View view){
            super(view);
            tvTitle = (TextView)view.findViewById(R.id.tv_title);
            tvTimestamp = (TextView)view.findViewById(R.id.tv_timestamp);
            tvPrice = (TextView)view.findViewById(R.id.tv_price);
        }
    }
    @Override
    public UsedOrderRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_used_order_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UsedOrderRecyclerAdapter.ViewHolder holder, int position) {
        holder.tvTimestamp.setText(dayTime.format(new Date(oldOrders.get(position).timestamp)));
        holder.tvTitle.setText(oldOrders.get(position).title);
        holder.tvPrice.setText(Integer.toString(oldOrders.get(position).price)+"원");

    }

    @Override
    public int getItemCount() {
        return oldOrders.size();
    }
}
