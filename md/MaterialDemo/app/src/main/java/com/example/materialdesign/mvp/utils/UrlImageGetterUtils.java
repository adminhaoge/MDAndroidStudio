package com.example.materialdesign.mvp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;

import com.example.materialdesign.mvp.utils.DrawableUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UrlImageGetterUtils implements Html.ImageGetter {
    Context context;
    private Drawable bitmapDrawable;
    private Map<String,Drawable> drawableMap = new HashMap<>();
    public UrlImageGetterUtils(Context context) {
        this.context = context;
    }

    @Override
    public Drawable getDrawable(final String source) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request build = new Request.Builder().url(source).build();
            Call call = okHttpClient.newCall(build);
            Response response = call.execute();
            Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
            bitmapDrawable = new BitmapDrawable(bitmap);
            DrawableUtils drawableUtils = new DrawableUtils(context);
            Drawable utils = drawableUtils.utils(bitmapDrawable);
            drawableMap.put(source,utils);
            return utils;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
