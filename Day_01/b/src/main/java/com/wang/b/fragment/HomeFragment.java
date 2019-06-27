package com.wang.b.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.b.R;
import com.wang.b.adapter.RecyAdapter;
import com.wang.b.bean.Bean;
import com.wang.b.model.ImpModel;
import com.wang.b.presenter.ImpPresenter;
import com.wang.b.view.VedioActivity;
import com.wang.b.view.ViewInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ViewInterface<Bean,String> {


    private View view;
    private RecyclerView mRv;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mRv = (RecyclerView) inflate.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();
    }

    private void initData() {
        ImpPresenter impPresenter = new ImpPresenter(new ImpModel(), this);
        impPresenter.getData();
    }

    @Override
    public void YES(Bean bean) {
        ArrayList<Bean.RecommendBean> recommend = (ArrayList<Bean.RecommendBean>) bean.getRecommend();
        RecyAdapter recyAdapter = new RecyAdapter(recommend, getActivity());
        mRv.setAdapter(recyAdapter);
        recyAdapter.setRecOnclick(new RecyAdapter.RecOnclick() {
            @Override
            public void on(int i) {
                startActivity(new Intent(getActivity(),VedioActivity.class));
            }
        });
    }

    @Override
    public void NO(String s) {
        Log.e("tag",s);
    }
}
