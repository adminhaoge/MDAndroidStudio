package com.example.materialdesign.mvp.model.http.rxjava_retrofit;


import android.util.Log;

import com.example.materialdesign.mvp.bean.BaseBaen;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.LoginRequestBean;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.contract.RecommendContract;
import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.ErrorHandlerSubscriber;
import com.example.materialdesign.mvp.model.http.error.httperror.RxTransformerUtils;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpEngine{
    public HttpEngine(){}
    private static int uid;
    private static String phone;
    private static String password;
    public HttpEngine(int uid) {
        this.uid = uid;
    }
    public HttpEngine(String phone ,String password) {
        this.phone =phone;
        this.password = password;

    }
    private Api api = HttpManager.getInstance().create(Api.class);
    private Api apiPost = PostHttpManager.getInstance().create(Api.class);

    //提供对外的访问实现接口
    public void getZhiHu(Subscriber<ResultBean> observer){
        setSubscribe(api.getPathData(),observer);
    }

    //提供对外的访问实现接口
    public void getZhiHuBody(Subscriber<ContentBean> observer){
        setSubscribe(api.getPathBody(uid),observer);
    }

    public void getLoginBody(ErrorHandlerSubscriber<LoginBean> observer){
        LoginRequestBean loginRequestBean = new LoginRequestBean();
        loginRequestBean.setEmail(phone);
        loginRequestBean.setPassword(password);
        setSubscribePost(apiPost.login(loginRequestBean),observer);
    }


    private <T> void setSubscribe(Observable<T> observable, Subscriber<T> observer) {
        observable
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private <T> void setSubscribePost(Observable<BaseBaen<T>> observable, ErrorHandlerSubscriber<T> observer) {
        observable
                .compose(RxTransformerUtils.applySchedulers())
                .subscribe(observer);
    }



}
