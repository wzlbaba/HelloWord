package com.wang.b.model;


import com.wang.b.bean.Bean;
import com.wang.b.callback.CallBack;

public interface InterfaceModel  {
    void getData(CallBack<Bean, String> callBack);
}
