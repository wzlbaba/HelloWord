package com.wang.a.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wang.a.api.MySrever;
import com.wang.a.bean.BsBean;
import com.wang.a.callback.CallBack;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Wang on 2019/6/26.
 */

public class ImpModel implements HomeModel{
    private ImpModel model;

    @Override
    public void getData(final CallBack callBack) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .get()
                .url("https://sport-data.dqdgame.com/sd/biz/live/index?platform=android&version=132")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<List<BsBean>>() {}.getType();
                List<BsBean> list = gson.fromJson(string, type);
                callBack.onOk(list);
            }
        });
    }

}
