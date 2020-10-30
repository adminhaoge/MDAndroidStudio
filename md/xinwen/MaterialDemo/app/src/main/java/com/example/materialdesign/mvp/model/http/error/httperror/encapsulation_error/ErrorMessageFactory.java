package com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error;

import android.content.Context;

import com.example.materialdesign.R;
import com.example.materialdesign.mvp.model.http.error.apierror.BaseException;

public class ErrorMessageFactory {
    public static String create(Context context,int code){
        String errorMsg = null;
        switch (code){
            case BaseException.UNKNOWN_ERROR:
                errorMsg = context.getResources().getString(R.string.error_unkown);
                break;
            case BaseException.SOCKET_TIMEOUT_ERROR:
                errorMsg = context.getResources().getString(R.string.error_socket_timeout);
                break;
            case BaseException.SOCKET_ERROR:
                errorMsg = context.getResources().getString(R.string.error_socket_unreachable);
                break;
            case BaseException.ERROR_HTTP_400:
                errorMsg = context.getResources().getString(R.string.error_http_400);
                break;
            case BaseException.ERROR_HTTP_404:
                errorMsg = context.getResources().getString(R.string.error_http_404);
                break;
            case BaseException.ERROR_HTTP_405:
                errorMsg = context.getResources().getString(R.string.error_http_405);
                break;
            case BaseException.ERROR_HTTP_500:
                errorMsg = context.getResources().getString(R.string.error_http_500);
                break;
            case BaseException.ERROR_API_SYSTEM:
                errorMsg = context.getResources().getString(R.string.error_system);
                break;
            case BaseException.ERROR_API_ACCOUNT_FREEZE:
                errorMsg = context.getResources().getString(R.string.error_account_freeze);
                break;
            case BaseException.ERROR_API_NO_PERMISSION:
                errorMsg = context.getResources().getString(R.string.error_api_no_perission);
                break;
            case BaseException.ERROR_API_LOGIN:
                errorMsg = context.getResources().getString(R.string.error_login);
                break;

                case BaseException.ERROR_TOKEN:
                    errorMsg = context.getResources().getString(R.string.error_token);
        }
        return errorMsg;
    }
}
