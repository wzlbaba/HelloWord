package com.wang.day03_lx.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wang.day03_lx.R;
import com.wang.day03_lx.bean.FuLi;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by Wang on 2019/5/30.
 */

class ScAdapter extends RecyclerView.Adapter{
Context context;
ArrayList<FuLi.ResultsBean> list;

    public ScAdapter(Context context, ArrayList<FuLi.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       RecyclerView.ViewHolder holder=null;
        if (viewType==1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
           holder=new ScHolder1(inflate);
        }
        if (viewType==2){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
            holder=new ScHolder2(inflate);
        }
        if (viewType==3){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);
            holder=new ScHolder3(inflate);
        }
        if (viewType==4){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item4, parent, false);
            holder=new ScHolder4(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type==1){
            ScHolder1 holder1= (ScHolder1) holder;
            holder1.tv.setText(list.get(position).getCreatedAt());
            Glide.with(context).load(list.get(position).getUrl()).into(((ScHolder1) holder).iv);
        }
        if (type==2){
            ScHolder2 holder2= (ScHolder2) holder;
            holder2.tv.setText(list.get(position).getCreatedAt());
            holder2.tv2.setText(list.get(position).getPublishedAt());
            Glide.with(context).load(list.get(position).getUrl()).into(holder2.iv);
            Glide.with(context).load(list.get(position).getUrl()).into(holder2.iv2);
        }
        if (type==3){
            ScHolder3 holder3= (ScHolder3) holder;
            holder3.tv.setText(list.get(position).getSource());
            Glide.with(context).load(list.get(position).getUrl()).into(holder3.iv);
        }
        if (type==4){
            ScHolder4 holder4= (ScHolder4) holder;
            holder4.tv.setText(list.get(position).getSource());
            Glide.with(context).load(list.get(position).getUrl()).into(holder4.iv);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%4==0){
            return 1;
        }
        if (position%4==1){
            return 2;
        }
        if (position%4==2){
            return 3;
        }else {
            return 4;
        }

    }

    class ScHolder1 extends RecyclerView.ViewHolder {

        private  TextView tv;
        private  ImageView iv;

        public ScHolder1(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.item1_tv);
            iv = itemView.findViewById(R.id.item1_iv);

        }
    }
    class ScHolder2 extends RecyclerView.ViewHolder {

        private  ImageView iv;
        private  ImageView iv2;
        private  TextView tv;
        private  TextView tv2;

        public ScHolder2(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item2_iv1);
            iv2 = itemView.findViewById(R.id.item2_iv2);
            tv = itemView.findViewById(R.id.item2_tv1);
            tv2 = itemView.findViewById(R.id.item2_tv2);
        }
    }
    class ScHolder3 extends RecyclerView.ViewHolder {

        private  ImageView iv;
        private  TextView tv;

        public ScHolder3(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item3_iv);
            tv = itemView.findViewById(R.id.item3_tv);

        }
    }
    class ScHolder4 extends RecyclerView.ViewHolder {

        private  TextView tv;
        private  ImageView iv;

        public ScHolder4(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item4_iv);
            tv = itemView.findViewById(R.id.item4_tv);
        }
    }
}
