package com.wang.b.api;


import com.wang.b.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AipServer {
    String URL = "https://bkbapi.dqdgame.com/app/tabs/android/";
    @GET("1.json?mark=gif&version=132")
    Observable<Bean> getData();
}
