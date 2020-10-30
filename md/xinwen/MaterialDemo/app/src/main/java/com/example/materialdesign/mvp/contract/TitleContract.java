package com.example.materialdesign.mvp.contract;

import com.example.materialdesign.mvp.base.IBaseVIew;
import com.example.materialdesign.mvp.bean.ResultBean;

import java.util.List;

import rx.Observable;

public interface TitleContract {
    interface ITitleModel{

        Observable<ResultBean> getPathData();

    }

    interface titleView extends IBaseVIew{
        void showRequest(List<ResultBean.StoriesBean> beans);
    }

}
