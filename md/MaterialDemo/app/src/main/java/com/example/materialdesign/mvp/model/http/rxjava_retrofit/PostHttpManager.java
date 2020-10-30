package com.example.materialdesign.mvp.model.http.rxjava_retrofit;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostHttpManager {
    private static final int DEFAULT_CONNECT_TIME = 10;
    private static final int DEFAULT_WRITE_TIME = 30;
    private static final int DEFAULT_READ_TIME = 30;
    private static final String REQUEST_PATH2 = "http://112.124.22.238:8081/course_api/cniaoplay/";
    private static PostHttpManager mPostHttpManager;
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    private  OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private PostHttpManager(){
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
                .baseUrl(REQUEST_PATH2)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


    }


    public <T> T create(Class<? extends T> service){
        return retrofit.create(service);
    }

    //单例模式
    public static PostHttpManager getInstance(){
        if (mPostHttpManager == null){
            synchronized (HttpManager.class){
                if (mPostHttpManager == null){
                    mPostHttpManager = new PostHttpManager();
                }
            }
        }
        return mPostHttpManager;
    }
}
