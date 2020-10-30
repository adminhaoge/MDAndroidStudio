package com.example.materialdesign.mvp.model.http.model;

import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.contract.TitleContract;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class TitleModel implements TitleContract.ITitleModel {
    private List<ResultBean.StoriesBean> beanList = new ArrayList<>();
    private ServiceApi api;

    public TitleModel(ServiceApi api) {
        this.api = api;
    }

    @Override
    public Observable<ResultBean> getPathData() {
        return api.getPathData();
    }
}