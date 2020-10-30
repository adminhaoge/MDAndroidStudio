package com.example.materialdesign.mvp.model.http.rxjava_retrofit;

import android.util.Log;

import com.example.materialdesign.mvp.base.IBaseModel;
import com.example.materialdesign.mvp.bean.BaseBaen;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.bean.User;
import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.ErrorHandlerSubscriber;
import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.RxErrorHandler;
import com.example.materialdesign.mvp.utils.ACache;
import com.example.materialdesign.mvp.view.application.AppApplication;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class HttpModel implements IBaseModel {

    RxErrorHandler rxErrorHandler = new RxErrorHandler(AppApplication.getContext());
    private List<ResultBean.StoriesBean> beanList = new ArrayList<>();
    private HttpEngine httpEngine = new HttpEngine();
    @Override
    public void getHttpRequest(FruitOnLoadListener fruitOnLoadListener) {

        httpEngine.getZhiHu(new Subscriber<ResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResultBean resultBean) {

            }
        });


    }

    @Override
    public void getHttpBody(BodyOnLoadListener bodyOnLoadListener) {
        httpEngine.getZhiHuBody(new Subscriber<ContentBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ContentBean contentBean) {
                String body = contentBean.getBody();
                bodyOnLoadListener.onComplete(body,contentBean);
            }
        });
    }

    @Override
    public void PostHttpBody(PostBodyOnLoadListener postbodyOnLoadListener) {
        httpEngine.getLoginBody(new ErrorHandlerSubscriber<LoginBean>(rxErrorHandler) {

            @Override
            public void onStart() {
                postbodyOnLoadListener.showLoading();
            }

            @Override
            public void onCompleted() {
                postbodyOnLoadListener.dismissLoading();
            }

            @Override
            public void onNext(LoginBean loginBean) {

                postbodyOnLoadListener.onComplete(loginBean);

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                postbodyOnLoadListener.dismissLoading();
            }
        });
    }


}