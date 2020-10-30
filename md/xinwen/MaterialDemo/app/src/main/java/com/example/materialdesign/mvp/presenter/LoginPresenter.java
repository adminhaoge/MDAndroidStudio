package com.example.materialdesign.mvp.presenter;

import android.util.Log;

import com.example.materialdesign.mvp.base.BasePresenter;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.contract.LoginContract;
import com.example.materialdesign.mvp.model.http.error.httperror.RxTransformerUtils;
import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.ErrorHandlerSubscriber;
import com.example.materialdesign.mvp.model.http.error.httperror.encapsulation_error.RxErrorHandler;
import com.example.materialdesign.mvp.utils.Constant;
import com.example.materialdesign.mvp.utils.ACache;
import com.example.materialdesign.mvp.utils.ValidatorUtils;
import com.example.materialdesign.mvp.view.application.AppApplication;
import com.hwangjr.rxbus.RxBus;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel,LoginContract.LoginView> {
    RxErrorHandler rxErrorHandler = new RxErrorHandler(AppApplication.getContext());
    @Inject
    public LoginPresenter(LoginContract.ILoginModel mModule, LoginContract.LoginView mView) {
        super(mModule, mView);
    }

    public void login(String phone,String pwd) {
        if (mModule != null && mView != null){
            if (!ValidatorUtils.isMobile(phone)){
                mView.checkPhoneError();
            }else {
                mView.checkPhoneSuccess();
            }
            mModule.login(phone, pwd).compose(RxTransformerUtils.applySchedulers())
                    .subscribe(new ErrorHandlerSubscriber<LoginBean>(rxErrorHandler) {
                        @Override
                        public void onCompleted() {
                            mView.dismissLoading();
                        }

                        @Override
                        public void onNext(LoginBean loginBean) {
                            mView.showPostRequestBody(loginBean);
                            saveUser(loginBean);
                            RxBus.get().post(loginBean.getUser());

                        }


                        @Override
                        public void onStart() {
                            super.onStart();
                            mView.showLoading();
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            mView.dismissLoading();
                        }
                    });
        }
    }
    private void saveUser(LoginBean loginBean) {
        ACache aCache = ACache.get(AppApplication.getContext());
        aCache.put(Constant.TOKEN,loginBean.getToken());
        aCache.put(Constant.USER,loginBean.getUser());
    }

}
