package com.example.materialdesign.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.materialdesign.mvp.base.BasePresenter;
import com.example.materialdesign.mvp.base.IBaseModel;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.LoginRequestBean;
import com.example.materialdesign.mvp.contract.RecommendContract;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.Constant;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.HttpModel;
import com.example.materialdesign.mvp.utils.ACache;
import com.example.materialdesign.mvp.view.application.AppApplication;
import com.hwangjr.rxbus.RxBus;

import javax.inject.Inject;

public class PostHttpPresenterBody extends BasePresenter<HttpModel, RecommendContract.View> {
    @Inject
    public PostHttpPresenterBody(HttpModel mModule, RecommendContract.View mView) {
        super(mModule, mView);
    }

    public void fetch() {
        if (mModule != null && mView != null){
            mModule.PostHttpBody(new IBaseModel.PostBodyOnLoadListener() {
                @Override
                public void onComplete(LoginBean loginBean) {
                    mView.showPostRequestBody(loginBean);
                    saveUser(loginBean);
                    RxBus.get().post(loginBean.getUser());
                }

                @Override
                public void showLoading() {
                    mView.showLoading();
                }

                @Override
                public void dismissLoading() {
                    mView.dismissLoading();
                }
            });
        }
    }
    private void saveUser(LoginBean loginBean) {
        ACache aCache = ACache.get(AppApplication.getContext());
        aCache.put(Constant.TOKEN,loginBean.getToken());
        aCache.put(Constant.USER,loginBean.getUser());
    }
}
