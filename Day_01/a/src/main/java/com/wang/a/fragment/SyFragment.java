package com.wang.a.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.a.R;
import com.wang.a.api.HomeServer;
import com.wang.a.api.MySrever;
import com.wang.a.bean.BsBean;
import com.wang.a.bean.ZiLiao;
import com.wang.a.model.ImpModel;
import com.wang.a.persenter.ImpPersen;
import com.wang.a.view.HomeView;
import com.youth.banner.Banner;

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
public class SyFragment extends Fragment implements HomeView {


    private RecyclerView rv;
    private Banner banner;
    private HomeAdapter homeAdapter;
    private ArrayList<BsBean> beans1;
    private ArrayList<ZiLiao.ArticlesBean> coverBeans;

    public SyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_sy, container, false);
        initView(inflate);
        new ImpPersen(new ImpModel(),this).getData();
        return inflate;
    }

    private void initView(View inflate) {
        banner = inflate.findViewById(R.id.banner);
        rv = inflate.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        beans1 = new ArrayList<>();
        coverBeans = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),beans1,coverBeans);
        rv.setAdapter(homeAdapter);
        initBanner();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HomeServer.url2).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        HomeServer homeServer = retrofit.create(HomeServer.class);
        Observable<ZiLiao> data2 = homeServer.getData2();
        data2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZiLiao>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZiLiao ziLiao) {
                        List<ZiLiao.ArticlesBean> articles = ziLiao.getArticles();
                        coverBeans.addAll(articles);
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

    private void initBanner() {
        banner.setImageLoader(new GlideImageLoader());
                 //初始化集合数据
                 // https://github.com/youth5201314/banner
                 List<String> mList = new ArrayList<>();
                 mList.add("https://ws1.sinaimg.cn/large/610dc034ly1fgllsthvu1j20u011in1p.jpg");
                 mList.add("https://ws1.sinaimg.cn/large/610dc034gy1fh9utulf4kj20u011itbo.jpg");
                 mList.add("https://ws1.sinaimg.cn/large/610dc034ly1fhfmsbxvllj20u00u0q80.jpg");
                 mList.add("https://ws1.sinaimg.cn/large/610dc034ly1fhj53yz5aoj21hc0xcn41.jpg");
                 mList.add("https://ws1.sinaimg.cn/large/610dc034ly1fhnqjm1vczj20rs0rswia.jpg");
                 mList.add("http://ww1.sinaimg.cn/large/610dc034ly1fhrcmgo6p0j20u00u00uu.jpg");
                 mList.add("http://ww1.sinaimg.cn/large/610dc034ly1fhxe0hfzr0j20u011in1q.jpg");
                 mList.add("http://ww3.sinaimg.cn/large/610dc034jw1fbd818kkwjj20u011hjup.jpg");
                 // 设置集合数据
        banner.setImages(mList);
                 // 开启轮播
        banner.start();
    }


    @Override
    public void onOk(final List<BsBean> list) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                beans1.addAll(list);
                homeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onNo(String s) {

    }
}
