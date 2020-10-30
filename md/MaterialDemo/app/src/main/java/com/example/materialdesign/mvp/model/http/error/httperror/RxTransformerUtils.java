package com.example.materialdesign.mvp.model.http.error.httperror;

import com.example.materialdesign.mvp.bean.BaseBaen;
import com.example.materialdesign.mvp.model.http.error.apierror.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *
 * 专门用于处理Api错误提示的Transformer。
 * 判断返回的status是否是正确值，如果是就正常输出，如果不是就return Observable.error错误返回。
 */

public class RxTransformerUtils {
//    public static<T> Observable.Transformer<T,T> applySchedulers(){
//        return (Observable<T> observable) -> observable.subscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    public static <T> Observable.Transformer<BaseBaen<T>, T> applySchedulers() {
        return new Observable.Transformer<BaseBaen<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBaen<T>> resultBeanObservable) {

                return resultBeanObservable.flatMap(new Func1<BaseBaen<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseBaen<T> baseBaen) {
                        if (baseBaen.success()){
                           return Observable.create(new Observable.OnSubscribe<T>() {
                                @Override
                                public void call(Subscriber<? super T> subscriber) {
                                    try {
                                        subscriber.onNext(baseBaen.getData());
                                        subscriber.onCompleted();
                                    }catch (Exception e){
                                        subscriber.onError(e);
                                    }
                                }
                            });
                        }else {
                           return Observable.error(new ApiException(baseBaen.getStatus(),baseBaen.getMessage()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
