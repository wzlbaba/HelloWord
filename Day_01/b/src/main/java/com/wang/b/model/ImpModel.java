package com.wang.b.model;


import com.wang.b.api.AipServer;
import com.wang.b.bean.Bean;
import com.wang.b.callback.CallBack;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImpModel implements InterfaceModel {

    @Override
    public void getData(final CallBack<Bean, String> callBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AipServer.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(AipServer.class).getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        if (bean != null) {
                            callBack.YES(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.NO(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
