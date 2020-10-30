package com.example.materialdesign.mvp.model.http.error.apierror;

public class ApiException extends BaseException {
    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
