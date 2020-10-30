package com.example.materialdesign.mvp.di.component;


import com.example.materialdesign.mvp.di.module.UtilsModule;
import com.example.materialdesign.mvp.di.scope.MyScope;
import com.example.materialdesign.mvp.view.activity.StartupPage;

import dagger.Component;
@MyScope
@Component(modules = UtilsModule.class,dependencies = AppComponent.class)
public interface UtilsComponent {
    void inject(StartupPage startupPage);
}
