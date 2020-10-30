package com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.materialdesign.mvp.utils.NetUtils;
import com.example.materialdesign.mvp.model.http.error.apierror.BaseException;
import com.example.materialdesign.mvp.view.activity.login.MainLogin;
import com.example.materialdesign.mvp.view.application.AppApplication;

public abstract class ErrorHandlerSubscriber<T> extends DefualtSubscriber<T> {

    public RxErrorHandler rxErrorHandler;

    public ErrorHandlerSubscriber(RxErrorHandler rxErrorHandler) {
        this.rxErrorHandler = rxErrorHandler;
    }


    /**
     *
     * @param e 错误的时候输出的内容
     * 把e传入到 BaseException中判断错误类型，然后利用ErrorMessageFactory类来转换成用户看的明白的错误字符串
     *          最终tomact出来。
     */

    @Override
    public void onError(Throwable e) {
            BaseException baseException = rxErrorHandler.handleError(e);
            if (baseException == null){
                e.printStackTrace();
                Log.e("TAG",e.getMessage());
            }else {
                rxErrorHandler.showErrorMessage(baseException);
                if (baseException.getCode() == BaseException.ERROR_TOKEN){
                    ToLogin();
                }
            }
    }

    private void ToLogin() {
        AppApplication.getContext().startActivity(new Intent(AppApplication.getContext(), MainLogin.class));
    }
}
