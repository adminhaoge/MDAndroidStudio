package com.example.materialdesign.mvp.utils.ImagerLoder;

import android.graphics.Bitmap;
import android.util.LruCache;

public class MemoryCacheObservable extends CacheObservable{
    int maxMemory = (int) Runtime.getRuntime().maxMemory();
    int maxSize = maxMemory / 8;
    LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>(maxSize){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    };
    @Override
    public Image getDataFromCache(String url) {
        Bitmap bitmap = mLruCache.get(url);
        if (bitmap != null){
            return new Image(url,bitmap);
        }
        return null;
    }

    @Override
    public void putDataToCache(Image image) {
        mLruCache.put(image.getUrl(),image.getmBitmap());
    }
}
