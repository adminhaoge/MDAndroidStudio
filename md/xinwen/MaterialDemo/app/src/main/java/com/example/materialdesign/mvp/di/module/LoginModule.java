package com.example.materialdesign.mvp.di.module;


import com.example.materialdesign.mvp.contract.LoginContract;
import com.example.materialdesign.mvp.model.http.model.ServiceApi;
import com.example.materialdesign.mvp.model.http.model.LoginModel;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    private LoginContract.LoginView view;

    public LoginModule(LoginContract.LoginView view) {
        this.view = view;
    }


    @Provides
    public LoginContract.LoginView ProvidesView(){
        return view;
    }
    


    @Provides
    public LoginContract.ILoginModel ProvidesLoginModel(ServiceApi api){
        return new LoginModel(api);
    }



}
