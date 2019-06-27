package com.wang.day_01.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.day_01.R;
import com.wang.day_01.bean.FuLi;
import com.wang.day_01.model.ImpModel;
import com.wang.day_01.persenter.ImpPersenter;
import com.wang.day_01.view.HomeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView{


    private Banner banner;
    private RecyclerView rv;
    private ArrayList<FuLi.ResultsBean> beans;
    private HomeAdapter homeAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        new ImpPersenter(new ImpModel(),this).getData();
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
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
        beans = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),beans);
        rv.setAdapter(homeAdapter);
    }

    private void initView(View inflate) {
        banner = inflate.findViewById(R.id.banner);
        rv = inflate.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void OnOk(List<FuLi.ResultsBean> list) {
            beans.addAll(list);
            homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnNo(String s) {

    }
}
