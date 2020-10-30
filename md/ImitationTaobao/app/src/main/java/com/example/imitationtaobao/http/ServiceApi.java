package com.example.imitationtaobao.http;

import com.example.imitationtaobao.bean.ClassLeftBean;
import com.example.imitationtaobao.bean.ClassWares;
import com.example.imitationtaobao.bean.ClassWaresPageBean;
import com.example.imitationtaobao.bean.CpDataBody;
import com.example.imitationtaobao.bean.ListBean;
import com.example.imitationtaobao.bean.SlideImage;
import com.example.imitationtaobao.bean.Wares;
import com.example.imitationtaobao.bean.WaresHot;
import com.example.imitationtaobao.bean.WaresPageBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ServiceApi {

    //广告轮播
    @GET("course_api/banner/query")
    Observable<List<SlideImage>> setSlide(@Query("type") int typeId);

    //推荐
    @GET("course_api/campaign/recommend")
    Observable<List<CpDataBody>> setRecommend();

    @GET("course_api/wares/hot")
    Observable<WaresPageBean<WaresHot>> setWares(@QueryMap Map<String,Integer> params);

    @GET("course_api/category/list")
    Observable<List<ClassLeftBean>> setClass();

    @GET("course_api/wares/list")
    Observable<ClassWaresPageBean<ClassWares>> setClassWares(@QueryMap Map<String,Integer> params);

    @GET("course_api/wares/campaign/list")
    Observable<Wares<ListBean>> setClassWaresActivity(@QueryMap Map<String,Object> params);

}
