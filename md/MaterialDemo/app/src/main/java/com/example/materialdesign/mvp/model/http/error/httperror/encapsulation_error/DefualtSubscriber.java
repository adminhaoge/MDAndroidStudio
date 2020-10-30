package com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * 继承重写Subscriber里面的方法，进行对应的封装。
 * 此类为父类
 * @param <T>
 */
public abstract class DefualtSubscriber<T> extends Subscriber<T> {
}
