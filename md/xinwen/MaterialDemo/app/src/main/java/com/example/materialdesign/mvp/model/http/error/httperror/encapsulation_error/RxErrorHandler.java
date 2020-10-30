package com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.materialdesign.mvp.base.IBaseVIew;
import com.example.materialdesign.mvp.model.http.error.apierror.ApiException;
import com.example.materialdesign.mvp.model.http.error.apierror.BaseException;
import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.ErrorMessageFactory;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;


import retrofit2.HttpException;

public class RxErrorHandler {
    private Context context;
    public RxErrorHandler(Context context) {
        this.context = context;
    }

    public BaseException handleError(Throwable e){
        BaseException exception = new BaseException();
        if (e instanceof ApiException){
            exception.setCode(((ApiException)e).getCode());
        }else if (e instanceof SocketException){
            exception.setCode(BaseException.SOCKET_ERROR);
        }else if (e instanceof SocketTimeoutException){
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        }else if (e instanceof HttpException){
            exception.setCode(((HttpException)e).code());
        }else if (e instanceof JSONException){
            exception.setCode(BaseException.JSON_ERROR);
        }else{
            //未知异常
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }
        exception.setDisplayMessage(ErrorMessageFactory.create(context,exception.getCode()));
        Log.e("TAG", String.valueOf(exception.getCode()));
        return exception;
    }
    public void showErrorMessage(BaseException e){
        Toast.makeText(context,e.getDisplayMessage(),Toast.LENGTH_SHORT).show();
    }

}
