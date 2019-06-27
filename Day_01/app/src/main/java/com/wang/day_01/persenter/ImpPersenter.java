package com.wang.day_01.persenter;

import com.wang.day_01.bean.FuLi;
import com.wang.day_01.callback.CallBack;
import com.wang.day_01.model.ImpModel;
import com.wang.day_01.view.HomeView;

import java.util.List;

/**
 * Created by Wang on 2019/6/25.
 */

public class ImpPersenter implements HomeP{
    private ImpModel impModel;
    private HomeView homeView;

    public ImpPersenter(ImpModel impModel, HomeView homeView) {
        this.impModel = impModel;
        this.homeView = homeView;
    }

    @Override
    public void getData() {
        if (impModel!=null){
            impModel.getData(new CallBack() {
                @Override
                public void OnOk(List<FuLi.ResultsBean> list) {
                    if (homeView!=null){
                        homeView.OnOk(list);
                    }
                }

                @Override
                public void OnNo(String s) {
                    if (homeView!=null){
                        homeView.OnNo("错误"+s);
                    }
                }
            });
        }
    }
}
