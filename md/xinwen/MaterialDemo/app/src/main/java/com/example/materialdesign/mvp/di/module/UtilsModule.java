package com.example.materialdesign.mvp.di.module;

import android.app.Application;

import com.example.materialdesign.mvp.utils.sharedUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {
    @Provides
    public sharedUtils ProvidesSharedUtils(Application mApplication){
        return new sharedUtils(mApplication);
    }
}
