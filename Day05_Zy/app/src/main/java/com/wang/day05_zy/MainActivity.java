package com.wang.day05_zy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ImageView mIv;
    private RecyclerView mRv;
    private NavigationView mNv;
    private ArrayList<Fuli.ResultsBean> beans;
    private HomeAdapter homeAdapter;

    // 王子龙
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mNv = (NavigationView) findViewById(R.id.nv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRv.setLayoutManager(gridLayoutManager);
        beans = new ArrayList<>();
        homeAdapter = new HomeAdapter(this,beans);
        mRv.setAdapter(homeAdapter);
        homeAdapter.setMyOnClick(new HomeAdapter.MyOnClick() {
            @Override
            public void MyOnclick(int position) {
                startActivity(new Intent(MainActivity.this,ErActivity.class));
            }
        });
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.url).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<Fuli> data = myServer.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Fuli>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Fuli fuli) {
                        List<Fuli.ResultsBean> results = fuli.getResults();
                        beans.addAll(results);
                        homeAdapter.notifyDataSetChanged();
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
