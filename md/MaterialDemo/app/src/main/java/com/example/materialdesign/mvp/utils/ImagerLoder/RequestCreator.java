package com.example.materialdesign.mvp.utils.ImagerLoder;

import android.content.Context;
import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class RequestCreator {
    private MemoryCacheObservable mMemoryCacheObservable;
    private DiskCacheObservable mDiskCacheObservable;
    private NetworkCacheObservable mNetworkCacheObservable;

    public RequestCreator(Context context) {
        mMemoryCacheObservable = new MemoryCacheObservable();
        mDiskCacheObservable = new DiskCacheObservable(context);
        mNetworkCacheObservable = new NetworkCacheObservable();
    }

    public Observable<Image> getImageFromMemory(String url){
        return mMemoryCacheObservable.getImage(url)
                .filter(new Func1<Image, Boolean>() {
                    @Override
                    public Boolean call(Image image) {
                        return image != null;
                    }
                }).doOnNext(new Action1<Image>() {
                    @Override
                    public void call(Image image) {
                        Log.e("TAG","get memory");
                    }
                });
    }

    public Observable<Image> getImageFromDisk(String url){
        return mDiskCacheObservable.getImage(url)
                .filter(new Func1<Image, Boolean>() {
                    @Override
                    public Boolean call(Image image) {
                        return image != null;
                    }
                })
                .doOnNext(new Action1<Image>() {
                    @Override
                    public void call(Image image) {
                        Log.e("TAG","get Disk");
                        mMemoryCacheObservable.putDataToCache(image);
                    }
                });
    }

    public Observable<Image> getImageFromNetwork(String url){
        return mNetworkCacheObservable.getImage(url)
                //判断是否为空如果为空，就不会继续下面的操作符的操作
                .filter(new Func1<Image, Boolean>() {
                    @Override
                    public Boolean call(Image image) {
                        return image != null;
                    }
                })
                .doOnNext(new Action1<Image>() {
                    @Override
                    public void call(Image image) {
                        Log.e("TAG","get Network");
                        if (image != null){
                            mDiskCacheObservable.putDataToCache(image);
                            mMemoryCacheObservable.putDataToCache(image);
                        }
                    }
                });
    }
}
