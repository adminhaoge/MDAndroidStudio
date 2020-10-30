package com.example.materialdesign.mvp.view.application;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.example.materialdesign.mvp.di.component.AppComponent;

import com.example.materialdesign.mvp.di.component.DaggerAppComponent;
import com.example.materialdesign.mvp.di.module.AppModule;
import com.example.materialdesign.mvp.di.module.HttpModule;

public class AppApplication extends Application {
    private AppComponent mAppComponent;
    private static Context mContext;

    public View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    public static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }
    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();

    }
}
