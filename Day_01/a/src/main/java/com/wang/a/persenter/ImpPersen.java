package com.wang.a.persenter;

import com.wang.a.bean.BsBean;
import com.wang.a.callback.CallBack;
import com.wang.a.model.ImpModel;
import com.wang.a.view.HomeView;

import java.util.List;

/**
 * Created by Wang on 2019/6/26.
 */

public class ImpPersen implements HomeP{
    private ImpModel impModel;
    private HomeView homeView;

    public ImpPersen(ImpModel impModel, HomeView homeView) {
        this.impModel = impModel;
        this.homeView = homeView;
    }

    @Override
    public void getData() {
        if (impModel!=null){
            impModel.getData(new CallBack() {
                @Override
                public void onOk(List<BsBean> list) {
                    if (homeView!=null){
                        homeView.onOk(list);
                    }
                }

                @Override
                public void onNo(String s) {
                    if (homeView!=null){
                        homeView.onNo(s);
                    }
                }
            });
        }
    }
}
