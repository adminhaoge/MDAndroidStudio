package com.example.materialdesign.mvp.utils.ImagerLoder;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxImageLoader {
    private static RxImageLoader singleton;
    private String murl;
    private RequestCreator mRequestCreator;
    private RxImageLoader(Builder builder){
     mRequestCreator = new RequestCreator(builder.context);
    }
    public static RxImageLoader with(Context context){
        if (singleton == null){
            synchronized (RxImageLoader.class){
                if (singleton == null){
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    public RxImageLoader load(String url){
        this.murl = url;
        return singleton;
    }

    public void into(ImageView imageview){
        Observable.concat(mRequestCreator.getImageFromMemory(murl),
                          mRequestCreator.getImageFromDisk(murl),
                          mRequestCreator.getImageFromNetwork(murl))
                .filter(new Func1<Image, Boolean>() {
                    @Override
                    public Boolean call(Image image) {
                        return image != null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Image>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                    }

                    @Override
                    public void onNext(Image image) {
                        imageview.setImageBitmap(image.getmBitmap());
                    }
                });
    }

    public static class Builder{
        private Context context;
        public Builder(Context context) {
            this.context = context;
        }

    public RxImageLoader build(){
            return new RxImageLoader(this);
    }
    }
}
