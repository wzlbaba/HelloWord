package com.wang.day05_zy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Wang on 2019/6/3.
 */

class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myHolder>{
Context context;
ArrayList<Fuli.ResultsBean> list;

    public HomeAdapter(Context context, ArrayList<Fuli.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        myHolder myHolder = new myHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getUrl()).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOnClick!=null){
                    myOnClick.MyOnclick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myHolder extends RecyclerView.ViewHolder {

        private  ImageView iv;

        public myHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item_iv);
        }
    }
    interface MyOnClick{
        void MyOnclick(int position);
    }
    private MyOnClick myOnClick;

    public void setMyOnClick(MyOnClick myOnClick) {
        this.myOnClick = myOnClick;
    }
}
