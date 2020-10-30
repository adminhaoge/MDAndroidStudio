package com.example.materialdesign.mvp.utils.ImagerLoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class NetworkCacheObservable extends CacheObservable {


    @Override
    public Image getDataFromCache(String url) {
        Bitmap bitmap = downloadImage(url);
        if (bitmap != null) {
            return new Image(url, bitmap);
        }

        return null;
    }

    @Override
    public void putDataToCache(Image image) {

    }

    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(url).openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
