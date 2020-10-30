package com.example.materialdesign.mvp.contract;

import com.example.materialdesign.mvp.base.IBasePresenter;
import com.example.materialdesign.mvp.base.IBaseVIew;
import com.example.materialdesign.mvp.bean.BaseBaen;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.ResultBean;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public interface LoginContract {

    interface ILoginModel{

    Observable<BaseBaen<LoginBean>> login(String phone,String pwd);

    }

    interface LoginView extends IBaseVIew {
        void checkPhoneError();
        void checkPhoneSuccess();
        void showPostRequestBody(LoginBean loginBean);
        void showLoading();
        void dismissLoading();
    }
}
