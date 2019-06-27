package com.wang.a.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wang.a.R;
import com.wang.a.bean.BsBean;
import com.wang.a.bean.ZiLiao;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by Wang on 2019/6/26.
 */

class HomeAdapter extends RecyclerView.Adapter{
    Context context;
    List<BsBean> list;
    List<ZiLiao.ArticlesBean> bean;

    public HomeAdapter(Context context, List<BsBean> list, List<ZiLiao.ArticlesBean> bean) {
        this.context = context;
        this.list = list;
        this.bean = bean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        if (viewType==1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            holder=new HomeHolder(inflate);
        }else if (viewType==2){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
            holder=new HomeHolder2(inflate);
        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);
            holder = new HomeHolder3(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType==1){
            HomeHolder homeHolder= (HomeHolder) holder;
            homeHolder.tv1.setText(list.get(position).getTeam_A_name());
            homeHolder.tv2.setText(list.get(position).getTeam_B_name());
            homeHolder.tv.setText(list.get(position).getMatch_title());
            homeHolder.tv_tv.setText(list.get(position).getCompetition_name());
            homeHolder.tv_tv2.setText(list.get(position).getRound_name());
            Glide.with(context).load(list.get(position).getTeam_A_logo()).apply(new RequestOptions().circleCrop()).into(homeHolder.iv);
            Glide.with(context).load(list.get(position).getTeam_B_logo()).apply(new RequestOptions().circleCrop()).into(homeHolder.iv2);
        }else if (itemViewType==2){
            HomeHolder2 homeHolder2= (HomeHolder2) holder;
            homeHolder2.tv.setText(bean.get(position).getShare_title());
            Glide.with(context).load(bean.get(position).getThumb()).apply(new RequestOptions().circleCrop()).into(homeHolder2.iv);
        }else {
            HomeHolder3 homeHolder3= (HomeHolder3) holder;
            homeHolder3.tv.setText(bean.get(position).getAdd_to_tab());
            Glide.with(context).load(bean.get(position).getThumb()).apply(new RequestOptions().circleCrop()).into(homeHolder3.iv);
            Glide.with(context).load(bean.get(position).getThumb()).into(homeHolder3.iv1);
        }
    }

    @Override
    public int getItemCount() {
    if (list!=null&&bean.size()>0){
        return list.size();
    }else {
       return bean.size();
    }

    }
    @Override
    public int getItemViewType(int position) {
        if (position==0&&bean.size()>0){
            return 1;
        }else if (position%3==0){
            return 2;
        }else{
            return 3;
        }

    }

    class HomeHolder extends RecyclerView.ViewHolder {
        private  ImageView iv;
        private  ImageView iv2;
        private  TextView tv;
        private  TextView tv2;
        private  TextView tv1;
        private  TextView tv_tv;
        private  TextView tv_tv2;

        public HomeHolder(View itemView) {
            super(itemView);
            tv_tv = itemView.findViewById(R.id.item_tv_tv1);
            tv_tv2 = itemView.findViewById(R.id.item_tv_tv2);
            this.tv1 = itemView.findViewById(R.id.item_iv_tv);
            tv2 = itemView.findViewById(R.id.item_iv_tv2);
            iv = itemView.findViewById(R.id.item_iv);
            iv2 = itemView.findViewById(R.id.item_iv2);
            this.tv = itemView.findViewById(R.id.item_tv);
        }
    }
    class HomeHolder2 extends RecyclerView.ViewHolder {

        private  ImageView iv;
        private  TextView tv;

        public HomeHolder2(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item2_iv);
            tv = itemView.findViewById(R.id.item2_tv);
        }
    }
    class HomeHolder3 extends RecyclerView.ViewHolder {

        private  ImageView iv;
        private  ImageView iv1;
        private  TextView tv;

        public HomeHolder3(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item3_iv);
            iv1 = itemView.findViewById(R.id.item3_iv2);
            tv = itemView.findViewById(R.id.item3_tv);
        }
    }
}
