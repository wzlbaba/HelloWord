package com.wang.day03_lx.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.day03_lx.R;
import com.wang.day03_lx.api.ScServer;
import com.wang.day03_lx.bean.FuLi;
import com.wang.day03_lx.model.ImpModel;
import com.wang.day03_lx.persenter.ImpPersenter;
import com.wang.day03_lx.view.ScView;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentFragment extends Fragment{


    private RecyclerView tv;
    private ArrayList<FuLi.ResultsBean> beans;
    private AtAdapter atAdapter;

    public AttentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_attent, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit build = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ScServer.url)
                .build();
        ScServer scServer = build.create(ScServer.class);
        Observable<FuLi> data = scServer.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuLi>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuLi fuLi) {
                        List<FuLi.ResultsBean> results = fuLi.getResults();
                        beans.addAll(results);
                        atAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View inflate) {
        tv = inflate.findViewById(R.id.at_rv);
        tv.setLayoutManager(new LinearLayoutManager(getActivity()));
        beans = new ArrayList<>();
        atAdapter = new AtAdapter(getActivity(),beans);
        tv.setAdapter(atAdapter);
    }
}
