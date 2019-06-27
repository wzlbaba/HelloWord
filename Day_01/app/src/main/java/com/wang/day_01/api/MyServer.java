package com.wang.day_01.api;

import com.wang.day_01.bean.FuLi;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Wang on 2019/6/25.
 */

public interface MyServer {
    String url="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/";

    @GET("7")
    Observable<FuLi> getData();
}
