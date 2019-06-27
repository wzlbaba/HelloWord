package com.wang.a.fragment;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by Wang on 2019/6/26.
 */

class GlideImageLoader extends ImageLoader{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        String imgPath = (String) path;
        //Picasso 加载图片简单用法
        Picasso.with(context).load(imgPath).into(imageView);
    }
}
