package com.wang.day03_lx.persenter;

import com.wang.day03_lx.bean.FuLi;
import com.wang.day03_lx.callback.CallBack;
import com.wang.day03_lx.model.ImpModel;
import com.wang.day03_lx.view.ScView;

import java.util.List;

/**
 * Created by Wang on 2019/5/30.
 */

public class ImpPersenter implements ScPersenter {
    private ImpModel impModel;
    private ScView scView;

    public ImpPersenter(ImpModel impModel, ScView scView) {
        this.impModel = impModel;
        this.scView = scView;
    }

    @Override
    public void getData() {
        if (impModel != null) {
            impModel.getData(new CallBack() {
                @Override
                public void onOK(List<FuLi.ResultsBean> list) {
                    if (scView != null) {
                        scView.onOK(list);
                    }
                }

                @Override
                public void onNO(String s) {
                    if (scView != null) {
                        scView.onNO(s);
                    }
                }
            });
        }
    }
}
