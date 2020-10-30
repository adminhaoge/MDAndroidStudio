package com.example.imitationtaobao.http;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyOkHttp{
    private static final int DEFAULT_CONNECT_TIME = 10;
    private static final int DEFAULT_WRITE_TIME = 30;
    private static final int DEFAULT_READ_TIME = 30;
    private String Url;
    public MyOkHttp(String url) {
        Url = url;
    }

    private OkHttpClient setOkHttpClient(){
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient  okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME,TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME,TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    public Retrofit setRetrofit(){
        OkHttpClient okHttpClient = setOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
