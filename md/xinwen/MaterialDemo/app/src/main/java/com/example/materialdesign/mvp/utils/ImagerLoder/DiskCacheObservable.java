package com.example.materialdesign.mvp.utils.ImagerLoder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class DiskCacheObservable  extends CacheObservable{
    private Context context;
    private DiskLruCache open;

    public DiskCacheObservable(Context context) {
        this.context = context;
        initDiskLruCache();
    }

    @Override
    public Image getDataFromCache(String url) {
        Bitmap bitmap = getDataFromDiskLruCache(url);
        if (bitmap != null){
            return new Image(url,bitmap);
        }
        return null;
    }

    @Override
    public void putDataToCache(Image image) {
        Observable.unsafeCreate(new Observable.OnSubscribe<Image>() {
            @Override
            public void call(Subscriber<? super Image> subscriber) {
                putDataFromDiskLruCache(image);
            }
        }).subscribeOn(Schedulers.io()).subscribe() ;
    }

    /**
     * 初始化创建文件缓存DiskLruCache
     */
    private void initDiskLruCache(){
        try {
            File image_cache = ImageUtils.getLruCacheDir(this.context, "Image_Cache");
            if (!image_cache.exists()){
                image_cache.mkdirs();
            }
            int appVersionCode = ImageUtils.getAppVersionCode(this.context);
            open = DiskLruCache.open(image_cache, appVersionCode, 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MD5加密key利用DiskLruCache获取缓存数据。
     * @param url url的key
     * @return 将缓存流放入BitmapFactory中进行转换，最终返回一个Bitmap。
     */
    private Bitmap getDataFromDiskLruCache(String url){
        FileInputStream fileInputStream = null;
        FileDescriptor fd = null;
        try {
            String UrlMd5Key = ImageUtils.hasKeyForCache(url);
            DiskLruCache.Snapshot snapshot = open.get(UrlMd5Key);
            if (snapshot != null){
                fileInputStream = (FileInputStream) snapshot.getInputStream(0);
                fd = fileInputStream.getFD();
            }
            Bitmap bitmap = null;
            if (fd != null){
                bitmap = BitmapFactory.decodeFileDescriptor(fd);
            }
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void putDataFromDiskLruCache(Image image){
        try {
            String key = ImageUtils.hasKeyForCache(image.getUrl());
            DiskLruCache.Editor edit = open.edit(key);
            if (edit != null){
                OutputStream outputStream = edit.newOutputStream(0);
                boolean isSuccessfull = download(image.getUrl(),outputStream);
                if (isSuccessfull){
                    edit.commit();
                }else {
                    edit.abort();
                }
                open.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean download(String url, OutputStream outputStream) {
        HttpURLConnection conn = null;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            bufferedInputStream = new BufferedInputStream(conn.getInputStream(), 8 * 1024);
            bufferedOutputStream = new BufferedOutputStream(outputStream, 8 * 1024);
            int len = 0;
            while ((len = bufferedInputStream.read()) != -1){
                bufferedOutputStream.write(len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (conn != null){
                conn.disconnect();
            }
            try {
                if (bufferedOutputStream != null){
                    bufferedOutputStream.close();
                }
                if (bufferedInputStream != null){
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
