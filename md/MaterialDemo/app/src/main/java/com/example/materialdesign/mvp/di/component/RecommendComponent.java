package com.example.materialdesign.mvp.di.component;

import com.example.materialdesign.mvp.base.BaseActivity;
import com.example.materialdesign.mvp.base.BaseFragment;
import com.example.materialdesign.mvp.di.module.HttpModule;
import com.example.materialdesign.mvp.di.module.RecommendModule;
import com.example.materialdesign.mvp.di.scope.MyScope;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.HttpEngine;
import com.example.materialdesign.mvp.view.activity.MainActivity;
import com.example.materialdesign.mvp.view.activity.StartupPage;
import com.example.materialdesign.mvp.view.activity.login.MainLogin;
import com.example.materialdesign.mvp.view.fragment.ProgressFragment;

import dagger.Component;

@MyScope
@Component(modules = RecommendModule.class,dependencies = AppComponent.class)
public interface RecommendComponent {
    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseFragment);
    void inject(StartupPage startupPage);
    void inject(HttpEngine httpEngine);
    void injcet(MainLogin login);
    void injcet(MainActivity mainActivity);
}
