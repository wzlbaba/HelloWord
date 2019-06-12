package com.wang.day03_lx.callback;

import com.wang.day03_lx.bean.FuLi;

import java.util.List;

/**
 * Created by Wang on 2019/5/30.
 */

public interface CallBack {
    void onOK(List<FuLi.ResultsBean> list);
    void onNO(String s);
}
