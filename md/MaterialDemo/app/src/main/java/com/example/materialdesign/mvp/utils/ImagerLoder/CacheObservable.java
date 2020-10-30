package com.example.materialdesign.mvp.utils.ImagerLoder;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class CacheObservable {
    public Observable<Image> getImage(String url){
        return Observable.unsafeCreate(new Observable.OnSubscribe<Image>() {
            @Override
            public void call(Subscriber<? super Image> subscriber) {
                //如果订阅没有解除才进行下一步操作
                if (!subscriber.isUnsubscribed()){
                    Image image = getDataFromCache(url);
                    subscriber.onNext(image);
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public abstract Image getDataFromCache(String url);

    public abstract void putDataToCache(Image image);

}
