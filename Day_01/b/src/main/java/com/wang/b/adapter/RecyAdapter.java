package com.wang.b.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.wang.b.R;
import com.wang.b.bean.Bean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import java.util.ArrayList;
public class RecyAdapter extends RecyclerView.Adapter {
    ArrayList<Bean.RecommendBean> list;
    Context mContext;

    public RecyAdapter(ArrayList<Bean.RecommendBean> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (i == 0){
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_banner,null);
            holder= new HolderBanner(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_item,null);
            holder = new Holder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0){
            HolderBanner h  = (HolderBanner) viewHolder;
            h.bann.setImages(list);
            h.bann.setImageLoader(new MyIma());
            h.bann.start();
        }
        if ( itemViewType == 2){
            Holder holder = (Holder) viewHolder;
            holder.tv.setText(list.get(i).getThumb());
            Glide.with(mContext).load(list.get(i).getThumb()).into(holder.iv);
        }
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mRecOnclick != null)
                    mRecOnclick.on(i);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public Holder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
    class HolderBanner extends RecyclerView.ViewHolder{
        Banner bann;
        public HolderBanner(@NonNull View itemView) {
            super(itemView);
            bann = itemView.findViewById(R.id.banner);
        }
    }
    class HolderVidio extends RecyclerView.ViewHolder{

        public HolderVidio(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 0;
        }
        if (position == 1){
            return 1;
        }
        return 2;
    }
    class MyIma extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Bean.RecommendBean bean = (Bean.RecommendBean) path;
            String thumb = bean.getThumb();
            Glide.with(context).load(thumb).into(imageView);
        }
    }
    public interface RecOnclick{
        void on(int i);
    }
    private RecOnclick mRecOnclick;

    public void setRecOnclick(RecOnclick recOnclick) {
        mRecOnclick = recOnclick;
    }
}
