package com.wang.day05_zy;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by Wang on 2019/6/3.
 */

class GlideImageLoader  extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        String imgpath= (String) path;
        Picasso.with(context).load(imgpath).into(imageView);
    }
}
