package com.example.materialdesign.mvp.di.component;

import com.example.materialdesign.mvp.di.module.LoginModule;
import com.example.materialdesign.mvp.di.scope.MyScope;
import com.example.materialdesign.mvp.view.activity.login.MainLogin;

import dagger.Component;
@MyScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(MainLogin login);
}
