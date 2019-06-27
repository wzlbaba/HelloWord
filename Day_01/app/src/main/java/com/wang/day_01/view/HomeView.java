package com.wang.day_01.view;

import com.wang.day_01.bean.FuLi;

import java.util.List;

/**
 * Created by Wang on 2019/6/25.
 */

public interface HomeView {
    void OnOk(List<FuLi.ResultsBean> list);
    void OnNo(String s);
}
