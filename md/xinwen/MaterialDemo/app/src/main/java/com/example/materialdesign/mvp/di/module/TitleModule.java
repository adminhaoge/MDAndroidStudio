package com.example.materialdesign.mvp.di.module;

import com.example.materialdesign.mvp.contract.TitleContract;
import com.example.materialdesign.mvp.model.http.model.ServiceApi;
import com.example.materialdesign.mvp.model.http.model.TitleModel;

import dagger.Module;
import dagger.Provides;

@Module
public class TitleModule {
    private TitleContract.titleView view;

    public TitleModule(TitleContract.titleView view) {
        this.view = view;
    }

    @Provides
    public TitleContract.titleView ProvidesView(){
        return view;
    }


    @Provides
    public TitleContract.ITitleModel ProvidesTitleModel(ServiceApi api){
        return new TitleModel(api);
    }
}
