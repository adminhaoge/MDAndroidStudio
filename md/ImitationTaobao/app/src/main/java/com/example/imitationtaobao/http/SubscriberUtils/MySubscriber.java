package com.example.imitationtaobao.http.SubscriberUtils;

import android.util.Log;
import android.widget.EditText;

import com.example.imitationtaobao.bean.SlideImage;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.util.List;

import retrofit2.HttpException;
import rx.Subscriber;

public abstract class MySubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("TAG", String.valueOf(e.fillInStackTrace()));
        if (e instanceof JsonSyntaxException){
            Log.e("TAG","json解析错误");
        }else if (e instanceof HttpException){
            Log.e("TAG", String.valueOf(((HttpException) e).code()));
        }
    }


}
