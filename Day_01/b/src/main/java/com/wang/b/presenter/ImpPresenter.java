package com.wang.b.presenter;

import android.view.View;

import com.wang.b.bean.Bean;
import com.wang.b.callback.CallBack;
import com.wang.b.model.ImpModel;
import com.wang.b.view.ViewInterface;


public class ImpPresenter implements InterfacePresenter , CallBack<Bean,String> {
    private ImpModel mImpModel;
    private ViewInterface mViewInterface;

    public ImpPresenter(ImpModel impModel, ViewInterface viewInterface) {
        mImpModel = impModel;
        mViewInterface = viewInterface;
    }

    @Override
    public void getData() {
        if (mImpModel != null)
            mImpModel.getData(this);
    }

    @Override
    public void YES(Bean bean) {
        if (mViewInterface != null)
            mViewInterface.YES(bean);
    }

    @Override
    public void NO(String s) {
        if (mViewInterface != null)
            mViewInterface.NO(s);
    }
}
