package com.example.materialdesign.mvp.presenter;

import com.example.materialdesign.mvp.base.BaseFragment;
import com.example.materialdesign.mvp.base.BasePresenter;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.contract.RecommendContract;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.HttpModel;
import com.example.materialdesign.mvp.base.IBaseModel;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public class HttpPresenterTitle extends BasePresenter<HttpModel,RecommendContract.View> {
    @Inject
    public HttpPresenterTitle(HttpModel mModule, RecommendContract.View mView) {
        super(mModule, mView);
    }





    public void fetch() {
        if (mModule != null && mView != null){
            mModule.getHttpRequest(new IBaseModel.FruitOnLoadListener() {
                @Override
                public void onComplete(List<ResultBean.StoriesBean> fruits) {
                    if (fruits != null){
                        mView.showRequest(fruits);
                    }
                }
            });

        }
    }


}
