package com.example.materialdesign.mvp.di.component;

import android.app.Application;

import com.example.materialdesign.mvp.di.module.AppModule;
import com.example.materialdesign.mvp.di.module.HttpModule;
import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.RxErrorHandler;
import com.example.materialdesign.mvp.model.http.model.ServiceApi;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    ServiceApi getServiceApi();
    Gson getGson();
    Application getApplication();
    RxErrorHandler getRxErrorHandler();
    OkHttpClient getOkHttpClient();
    Retrofit getRetrofit();
}
