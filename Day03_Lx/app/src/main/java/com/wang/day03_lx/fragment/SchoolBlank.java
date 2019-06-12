package com.wang.day03_lx.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.day03_lx.R;
import com.wang.day03_lx.bean.FuLi;
import com.wang.day03_lx.model.ImpModel;
import com.wang.day03_lx.persenter.ImpPersenter;
import com.wang.day03_lx.view.ScView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolBlank extends Fragment implements ScView{

//H1810B        王子龙
    private RecyclerView rv;
    private ArrayList<FuLi.ResultsBean> beans;
    private ScAdapter scAdapter;

    public SchoolBlank() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_school, container, false);

        initView(inflate);

        return inflate;
    }

    private void initView(View inflate) {
        rv = inflate.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        beans = new ArrayList<>();
        scAdapter = new ScAdapter(getActivity(),beans);
        rv.setAdapter(scAdapter);
        new ImpPersenter(new ImpModel(),this).getData();
    }

    @Override
    public void onOK( List<FuLi.ResultsBean> list) {
                beans.addAll(list);
                scAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNO( String s) {
                Log.e("Tag","错误"+s);
    }
}
