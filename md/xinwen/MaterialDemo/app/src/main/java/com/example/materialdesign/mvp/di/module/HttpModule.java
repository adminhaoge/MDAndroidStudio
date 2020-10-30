package com.example.materialdesign.mvp.di.module;

import android.app.Application;

import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.ErrorHandlerSubscriber;
import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.RxErrorHandler;
import com.example.materialdesign.mvp.model.http.model.ServiceApi;
import com.example.materialdesign.mvp.utils.CommonParamsInterceptor;
import com.example.materialdesign.mvp.view.application.AppApplication;
import com.google.gson.Gson;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {
    RxErrorHandler rxErrorHandler = new RxErrorHandler(AppApplication.getContext());
    private static final int DEFAULT_CONNECT_TIME = 10;
    private static final int DEFAULT_WRITE_TIME = 30;
    private static final int DEFAULT_READ_TIME = 30;
    private static final String REQUEST_PATH_GET = "https://news-at.zhihu.com/";
    private static final String REQUEST_PATH_POST = "http://112.124.22.238:8081/course_api/cniaoplay/";
    @Provides
    @Singleton
    public OkHttpClient ProvidesOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new CommonParamsInterceptor())
                .connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    @Provides
    public Retrofit ProvidesRetrofit(OkHttpClient okHttpClient){
            Retrofit build = new Retrofit.Builder()
                    .baseUrl(REQUEST_PATH_GET)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            return build;

    }

    @Provides
    @Singleton
    public ServiceApi ProvidesApi(Retrofit retrofit){
        return retrofit.create(ServiceApi.class);
    }

    @Provides
    @Singleton
    public Gson providesGson(){
        return new Gson();
    }

    @Provides
    @Singleton
    public RxErrorHandler providesRxErrorHandler(Application mApplication){
        return new RxErrorHandler(mApplication);
    }

}
