package com.example.materialdesign.mvp.model.http.rxjava_retrofit;


import com.example.materialdesign.mvp.bean.BaseBaen;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.LoginRequestBean;
import com.example.materialdesign.mvp.bean.ResultBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface Api {
    @GET("api/4/news/latest")
    Observable<ResultBean> getPathData();

    @GET("api/4/news/{id}")
    Observable<ContentBean> getPathBody(@Path("id") int id);

    @POST("login")
    Observable<BaseBaen<LoginBean>> login(@Body LoginRequestBean param);


}
