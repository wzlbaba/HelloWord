package com.wang.a.api;

import com.wang.a.bean.BsBean;
import com.wang.a.bean.ZiLiao;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Wang on 2019/6/26.
 */

public interface MySrever {
    String url = "https://sport-data.dqdgame.com/sd/biz/live/";

    @GET("index?platform=android&version=132")
    Observable<BsBean> getData();

}
