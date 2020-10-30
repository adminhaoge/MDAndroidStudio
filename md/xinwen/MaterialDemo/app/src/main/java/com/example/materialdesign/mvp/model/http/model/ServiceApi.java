package com.example.materialdesign.mvp.model.http.model;


import com.example.materialdesign.mvp.bean.BaseBaen;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.LoginRequestBean;
import com.example.materialdesign.mvp.bean.ResultBean;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;


public interface ServiceApi {

    @Headers("urlname:test1")
    @GET("api/4/news/latest")
    Observable<ResultBean> getPathData();

    @Headers("urlname:test2")
    @GET("api/4/news/{id}")
    Observable<ContentBean> getPathBody(@Path("id") int id);


    @Headers("urlname:test3")
    @POST("course_api/cniaoplay/login")
    Observable<BaseBaen<LoginBean>> login(@Body LoginRequestBean param);


}
