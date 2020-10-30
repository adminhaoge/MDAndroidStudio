package com.example.materialdesign.mvp.di.module;

import android.app.Application;

import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.RxErrorHandler;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.Api;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {
    private static final int DEFAULT_CONNECT_TIME = 10;
    private static final int DEFAULT_WRITE_TIME = 30;
    private static final int DEFAULT_READ_TIME = 30;
    private static final String REQUEST_PATH = "https://news-at.zhihu.com/";


    @Provides
    @Singleton
    public OkHttpClient ProvidesOkHttpClient(Application application , Gson gson){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    public Retrofit ProvidesRetrofit(OkHttpClient okHttpClient){
        Retrofit build = new Retrofit.Builder()
                .baseUrl(REQUEST_PATH)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return build;
    }

    @Provides
    @Singleton
    public Api ProvidesApi(Retrofit retrofit){
        return retrofit.create(Api.class);
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
