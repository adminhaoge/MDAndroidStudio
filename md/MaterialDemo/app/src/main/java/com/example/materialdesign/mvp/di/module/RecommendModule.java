package com.example.materialdesign.mvp.di.module;


import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.materialdesign.mvp.base.BaseActivity;
import com.example.materialdesign.mvp.base.BaseFragment;
import com.example.materialdesign.mvp.contract.RecommendContract;
import com.example.materialdesign.mvp.di.scope.MyScope;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.Api;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.HttpEngine;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.HttpModel;
import com.example.materialdesign.mvp.presenter.HttpPresenterBody;
import com.example.materialdesign.mvp.presenter.HttpPresenterTitle;
import com.example.materialdesign.mvp.utils.sharedUtils;
import com.example.materialdesign.mvp.view.application.AppApplication;

import java.lang.ref.WeakReference;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.http.PUT;

@Module
public class RecommendModule {
    private RecommendContract.View view;

    public RecommendModule(RecommendContract.View view) {
        this.view = view;
    }

    //方法一
//    @Provides
//    public HttpPresenterTitle ProvidesHttpPresenterTitle(RecommendContract.View view){
//        return new HttpPresenterTitle(new HttpModel(),view);
//    }
//
//    @Provides
//    public HttpPresenterBody ProvidesHttpPresenterBody(RecommendContract.View view){
//        return new HttpPresenterBody(new HttpModel(),view);
//    }


    @Provides
    public RecommendContract.View ProvidesView(){
        return view;
    }
    @Provides
    public HttpModel ProvidesHttpModel(){
        return new HttpModel();
    }

    @Provides
    public sharedUtils ProvidesSharedUtils(Application mApplication){
        return new sharedUtils(mApplication);
    }





}
