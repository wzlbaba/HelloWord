package com.wang.day05_zy;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Wang on 2019/6/3.
 */

public interface MyServer {
    String url="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/";

    @GET("1")
    Observable<Fuli> getData();
}
