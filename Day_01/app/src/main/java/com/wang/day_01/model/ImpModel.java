package com.wang.day_01.model;

import com.wang.day_01.api.MyServer;
import com.wang.day_01.bean.FuLi;
import com.wang.day_01.callback.CallBack;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wang on 2019/6/25.
 */

public class ImpModel implements HomeModel{
    @Override
    public void getData(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.url)
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<FuLi> data = myServer.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuLi>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuLi resultsBean) {
                        List<FuLi.ResultsBean> results = resultsBean.getResults();
                        callBack.OnOk(results);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
