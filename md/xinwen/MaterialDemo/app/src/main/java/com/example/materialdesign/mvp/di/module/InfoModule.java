package com.example.materialdesign.mvp.di.module;

import com.example.materialdesign.mvp.contract.InfoBodyContract;
import com.example.materialdesign.mvp.model.http.model.ServiceApi;
import com.example.materialdesign.mvp.model.http.model.InfoBodyModel;

import dagger.Module;
import dagger.Provides;

@Module
public class InfoModule {
    private InfoBodyContract.InfoBodyView view;

    public InfoModule(InfoBodyContract.InfoBodyView view) {
        this.view = view;
    }


    @Provides
    public InfoBodyContract.InfoBodyView ProvidesView(){
        return view;
    }

    @Provides
    public InfoBodyContract.IInfoBodyModel ProvidesInfoBodyModel(ServiceApi api){
        return new InfoBodyModel(api);
    }
}
