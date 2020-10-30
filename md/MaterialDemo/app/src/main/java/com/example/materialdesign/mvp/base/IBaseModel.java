package com.example.materialdesign.mvp.base;

import android.util.Log;

import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.ResultBean;

import java.util.List;

public interface IBaseModel {
    /**
     * journalism页面的数据监听加载
     * @param fruitOnLoadListener
     */
    void getHttpRequest(FruitOnLoadListener fruitOnLoadListener);
    //监听数据返回
    interface FruitOnLoadListener {
        void onComplete(List<ResultBean.StoriesBean> fruits);
    }

    /**
     * infoActivity也买你的数据监听加载
     * @param bodyOnLoadListener
     */
    void getHttpBody(BodyOnLoadListener bodyOnLoadListener);
    //监听数据返回
    interface BodyOnLoadListener {
        void onComplete(String data, ContentBean contentBean);
    }

    void PostHttpBody(PostBodyOnLoadListener postbodyOnLoadListener);
    //监听数据返回
    interface PostBodyOnLoadListener {
        void onComplete(LoginBean loginBean);
        void showLoading();
        void dismissLoading();
    }


}