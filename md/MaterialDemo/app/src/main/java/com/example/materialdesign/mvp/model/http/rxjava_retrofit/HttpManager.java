package com.example.materialdesign.mvp.model.http.rxjava_retrofit;

import com.example.materialdesign.mvp.di.component.AppComponent;
import com.example.materialdesign.mvp.view.application.AppApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static final int DEFAULT_CONNECT_TIME = 10;
    private static final int DEFAULT_WRITE_TIME = 30;
    private static final int DEFAULT_READ_TIME = 30;
    private static final String REQUEST_PATH = "https://news-at.zhihu.com/";
    private static HttpManager mHttpManager;
    private  OkHttpClient okHttpClient;
    private final Retrofit retrofit;

    private HttpManager(){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(new CommonParamsInterceptor())
                    .connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)
                    .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(REQUEST_PATH)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();




    }



    public <T> T create(Class<? extends T> service){
        return retrofit.create(service);
    }

    //单例模式
    public static HttpManager getInstance(){
        if (mHttpManager == null){
            synchronized (HttpManager.class){
                if (mHttpManager == null){
                    mHttpManager = new HttpManager();
                }
            }
        }
        return mHttpManager;
    }
}
