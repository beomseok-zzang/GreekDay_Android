package com.example.beomseok.greekday.adapter;

/**
 * Created by bumbum on 2016-06-02.
 */


import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beomseok.greekday.R;
import com.example.beomseok.greekday.model.Message;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder> {
    private static final int TYPE_ME = 0;
    private static final int TYPE_YOU = 1;
    ArrayList<Message> messages;

    LayoutInflater inflater;

    SimpleDateFormat dayTime = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss");

    public ChatRecyclerAdapter(ArrayList<Message> messages,LayoutInflater inflater){
        this.messages = messages;
        this.inflater = inflater;

    }
    @Override
    public int getItemViewType(int position) {
        return messages.get(position).isOwner ? TYPE_YOU : TYPE_ME;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMessage;
        TextView txtInfo;
        ImageView imgView;

        public ViewHolder(View view) {
            super(view);
            txtMessage = (TextView) view.findViewById(R.id.txtMessage);
            txtInfo = (TextView) view.findViewById(R.id.txt_info);
            imgView = (ImageView) view.findViewById(R.id.img_profile);
        }
    }
    @Override
    public ChatRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ME) {
            return new ViewHolder(inflater.inflate(R.layout.chat_me_list_item,parent,false));
        } else {
            return new ViewHolder(inflater.inflate(R.layout.chat_you_list_item,parent,false));
        }
    }
    public void addItem(Message message){
        messages.add(message);
        notifyItemInserted(messages.size() - 1);

    }
    @Override
    public void onBindViewHolder(ChatRecyclerAdapter.ViewHolder holder, int position) {
        final ChatRecyclerAdapter.ViewHolder cHolder = holder;
        cHolder.txtMessage.setText(messages.get(position).text);
        String date = dayTime.format(new Date(messages.get(position).date));
        cHolder.txtInfo.setText(date);


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }



}




