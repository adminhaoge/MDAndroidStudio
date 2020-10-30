package com.example.materialdesign.mvp.model.http.model;

import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.contract.InfoBodyContract;

import rx.Observable;

public class InfoBodyModel implements InfoBodyContract.IInfoBodyModel {

    private ServiceApi api;

    public InfoBodyModel(ServiceApi api) {
        this.api = api;
    }


    @Override
    public Observable<ContentBean> onComplete(int infoId) {

        return api.getPathBody(infoId);
    }
}
