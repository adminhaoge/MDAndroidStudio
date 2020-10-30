package com.example.materialdesign.mvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.materialdesign.mvp.di.component.AppComponent;
import com.example.materialdesign.mvp.view.application.AppApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity  {
    Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        unbinder = ButterKnife.bind(this);
        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        setupActivityComponent(appComponent);
        initData();

    }

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected abstract void initData();

    protected abstract int setLayout();



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != Unbinder.EMPTY){
            unbinder.unbind();
        }

    }



}
