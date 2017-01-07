package com.example.beomseok.greekday.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beomseok.greekday.NoticeDetailActivity;
import com.example.beomseok.greekday.R;
import com.example.beomseok.greekday.model.Notice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 김승욱 on 2016-12-16.
 */

public class NoticeRecyclerAdapter extends RecyclerView.Adapter<NoticeRecyclerAdapter.ViewHolder> {
    SimpleDateFormat dayTime;
    ArrayList<Notice> notices;
    Activity activity;
    public NoticeRecyclerAdapter(ArrayList<Notice> notices, Activity activity){
        dayTime = new SimpleDateFormat("yyyy년 MM월 dd일");
        this.notices = notices;
        this.activity = activity;
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
    public void onBindViewHolder(final NoticeRecyclerAdapter.ViewHolder holder, final int position) {
        holder.tvTimestamp.setText(dayTime.format(new Date(notices.get(position).timestamp)));
        holder.tvTitle.setText(notices.get(position).title);
        final Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, NoticeDetailActivity.class);
                intent.putExtra("notice_data",notices.get(position));
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notices.size();
    }
}
