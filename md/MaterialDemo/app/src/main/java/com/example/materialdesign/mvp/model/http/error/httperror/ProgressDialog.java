package com.example.materialdesign.mvp.model.http.error.httperror;

import android.content.Context;

import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.ErrorHandlerSubscriber;
import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.RxErrorHandler;

public abstract class ProgressDialog<T> extends ErrorHandlerSubscriber<T> {
    private Context context;
    private android.app.ProgressDialog dialog;

    public ProgressDialog(Context context, RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
        this.context = context;

    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }


    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissProgressDialog();
    }



    private void initDialog() {
        dialog = new android.app.ProgressDialog(context);
        dialog.setTitle("加载页面");
        dialog.setMessage("加载中.....");
        dialog.show();
    }


    private void dismissProgressDialog() {
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }


}
