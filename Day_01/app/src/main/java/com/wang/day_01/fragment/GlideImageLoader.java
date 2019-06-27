package com.wang.day_01.fragment;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Wang on 2019/6/25.
 */

class GlideImageLoader extends ImageLoader{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        String imgPath = (String) path;
        //Picasso 加载图片简单用法
        Picasso.with(context).load(imgPath).into(imageView);

    }
}
