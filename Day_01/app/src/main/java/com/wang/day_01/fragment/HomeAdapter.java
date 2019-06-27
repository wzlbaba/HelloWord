package com.wang.day_01.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wang.day_01.R;
import com.wang.day_01.bean.FuLi;

import java.util.ArrayList;

/**
 * Created by Wang on 2019/6/25.
 */

class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<FuLi.ResultsBean> list;

    public HomeAdapter(Context context, ArrayList<FuLi.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        if (viewType==1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
            holder=new HomeHolder(inflate);
        }
        if (viewType==2){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
            holder=new HomeHolder2(inflate);
        }
     return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type==1){
            HomeHolder homeHolder= (HomeHolder) holder;
            homeHolder.tv.setText(list.get(position).getPublishedAt());
            Glide.with(context).load(list.get(position).getUrl()).into(homeHolder.iv);
        }
        if (type==2){
            HomeHolder2 homeHolder2= (HomeHolder2) holder;
            homeHolder2.tv.setText(list.get(position).getPublishedAt());
            Glide.with(context).load(list.get(position).getUrl()).into(homeHolder2.iv);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==1){
            return 1;
        }
        return 2;
    }

    public class HomeHolder extends RecyclerView.ViewHolder {

        private  ImageView iv;
        private  TextView tv;

        public HomeHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item_iv);
            tv = itemView.findViewById(R.id.item_tv);
        }
    }
    public class HomeHolder2 extends RecyclerView.ViewHolder {

        private  ImageView iv;
        private  TextView tv;

        public HomeHolder2(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item2_iv);
            tv = itemView.findViewById(R.id.item2_tv);
        }
    }
}
