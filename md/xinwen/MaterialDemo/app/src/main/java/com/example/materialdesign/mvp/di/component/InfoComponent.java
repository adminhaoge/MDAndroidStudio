package com.example.materialdesign.mvp.di.component;

import com.example.materialdesign.mvp.di.module.InfoModule;
import com.example.materialdesign.mvp.di.scope.MyScope;
import com.example.materialdesign.mvp.view.activity.InfoActivity;

import dagger.Component;

@MyScope
@Component(modules = InfoModule.class,dependencies = AppComponent.class)
public interface InfoComponent {
    void inject(InfoActivity infoActivity);
}
