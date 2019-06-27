package com.wang.a.callback;

import com.wang.a.bean.BsBean;

import java.util.List;

/**
 * Created by Wang on 2019/6/26.
 */

public interface CallBack {
    void onOk(List<BsBean> list);
    void onNo(String s);
}
