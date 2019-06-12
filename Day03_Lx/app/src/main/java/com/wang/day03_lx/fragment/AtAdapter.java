package com.wang.day03_lx.fragment;

import android.content.Context;
import android.media.Image;
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

/**
 * Created by Wang on 2019/5/30.
 */

class AtAdapter extends RecyclerView.Adapter<AtAdapter.AtHolder>{
    Context context;
    ArrayList<FuLi.ResultsBean> list;

    public AtAdapter(Context context, ArrayList<FuLi.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AtHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.at_item, parent, false);
        AtHolder holder = new AtHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AtHolder holder, int position) {
            holder.tv.setText(list.get(position).getType());
        Glide.with(context).load(list.get(position).getUrl()).into(holder.iv);
        Glide.with(context).load(list.get(position).getUrl()).into(holder.iv2);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AtHolder extends RecyclerView.ViewHolder {

        private  TextView tv;
        private  ImageView iv2;
        private  ImageView iv;

        public AtHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item_iv1);
            iv2 = itemView.findViewById(R.id.item_iv2);
            tv = itemView.findViewById(R.id.item_tv);
        }
    }
}
