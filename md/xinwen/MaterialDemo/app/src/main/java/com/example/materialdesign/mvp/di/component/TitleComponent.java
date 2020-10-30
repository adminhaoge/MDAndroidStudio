package com.example.materialdesign.mvp.di.component;


import com.example.materialdesign.mvp.di.module.TitleModule;
import com.example.materialdesign.mvp.di.scope.MyScope;
import com.example.materialdesign.mvp.view.fragment.Journalism;

import dagger.Component;
@MyScope
@Component(modules = TitleModule.class,dependencies = AppComponent.class)
public interface TitleComponent {
    void inject(Journalism journalism);
}
