package com.wang.day03_lx.api;

import com.wang.day03_lx.bean.FuLi;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Wang on 2019/5/30.
 */

public interface ScServer {
    String url="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/";

    @GET("1")
    Observable<FuLi> getData();
}
