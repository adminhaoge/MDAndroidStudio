package com.example.materialdesign.mvp.model.http.model;

import com.example.materialdesign.mvp.bean.BaseBaen;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.LoginRequestBean;
import com.example.materialdesign.mvp.contract.LoginContract;
import com.example.materialdesign.mvp.utils.ACache;
import com.example.materialdesign.mvp.utils.Constant;
import com.example.materialdesign.mvp.view.application.AppApplication;

import rx.Observable;

public class LoginModel implements LoginContract.ILoginModel {
    private ServiceApi api;

    public LoginModel(ServiceApi api) {
        this.api = api;
    }


    @Override
    public Observable<BaseBaen<LoginBean>> login(String phone, String pwd) {
        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(pwd);
        return api.login(param);
    }
}
