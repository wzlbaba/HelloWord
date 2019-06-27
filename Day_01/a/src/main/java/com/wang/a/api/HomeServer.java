package com.wang.a.api;

import com.wang.a.bean.ZiLiao;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Wang on 2019/6/26.
 */

public interface HomeServer {
    String url2="https://bkbapi.dqdgame.com/app/tabs/android/";

    @GET("1.json?mark=gif&version=132")
    Observable<ZiLiao> getData2();

}
