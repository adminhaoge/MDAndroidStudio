package com.example.materialdesign.mvp.presenter;

import com.example.materialdesign.mvp.base.BasePresenter;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.contract.RecommendContract;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.HttpModel;
import com.example.materialdesign.mvp.base.IBaseModel;

import javax.inject.Inject;

public class HttpPresenterBody extends BasePresenter<HttpModel,RecommendContract.View> {
    @Inject
    public HttpPresenterBody(HttpModel mModule, RecommendContract.View mView) {
        super(mModule, mView);
    }


    public void fetch() {
        mModule.getHttpBody(new IBaseModel.BodyOnLoadListener() {
            @Override
            public void onComplete(String data, ContentBean contentBean) {
                mView.showRequestBody(data,contentBean);
            }
        });
    }


}
