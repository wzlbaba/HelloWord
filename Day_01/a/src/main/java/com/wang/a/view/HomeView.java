package com.wang.a.view;

import com.wang.a.bean.BsBean;

import java.util.List;

/**
 * Created by Wang on 2019/6/26.
 */

public interface HomeView {
    void onOk(List<BsBean> list);
    void onNo(String s);
}
