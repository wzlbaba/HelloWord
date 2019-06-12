package com.wang.day03_lx.view;

import com.wang.day03_lx.bean.FuLi;

import java.util.List;

/**
 * Created by Wang on 2019/5/30.
 */

public interface ScView {
    void onOK(List<FuLi.ResultsBean> list);
    void onNO(String s);
}
