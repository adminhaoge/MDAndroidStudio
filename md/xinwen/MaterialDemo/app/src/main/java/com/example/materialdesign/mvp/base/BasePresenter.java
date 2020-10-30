package com.example.materialdesign.mvp.base;

import java.lang.ref.WeakReference;

public class BasePresenter<M,V extends IBaseVIew> {
    protected M mModule;
    protected V mView;

    public BasePresenter(M mModule, V mView) {
        this.mModule = mModule;
        this.mView = mView;
    }

    //弱引用绑定
    public void attachView(V view) {
        mView = view;
    }

    //解绑
    public void detachView() {
        if (mView != null){
            mView = null;
        }
    }
}
