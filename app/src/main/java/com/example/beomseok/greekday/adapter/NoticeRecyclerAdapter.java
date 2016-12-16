package com.example.beomseok.greekday.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beomseok.greekday.R;
import com.example.beomseok.greekday.model.Notice;

import java.util.ArrayList;

/**
 * Created by 김승욱 on 2016-12-16.
 */

public class NoticeRecyclerAdapter extends RecyclerView.Adapter<NoticeRecyclerAdapter.ViewHolder> {

    ArrayList<Notice> notices;
    public NoticeRecyclerAdapter(ArrayList<Notice> notices){
        this.notices = notices;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvTimestamp;

        public ViewHolder(View view){
            super(view);
            tvTitle = (TextView)view.findViewById(R.id.tv_title);
            tvTimestamp = (TextView)view.findViewById(R.id.tv_timestamp);
        }
    }
    @Override
    public NoticeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_notice_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoticeRecyclerAdapter.ViewHolder holder, int position) {
        holder.tvTimestamp.setText(Long.toString(notices.get(position).timestamp));
        holder.tvTitle.setText(notices.get(position).title);

    }

    @Override
    public int getItemCount() {
        return notices.size();
    }
}
