package com.example.materialdesign.mvp.contract;

import com.example.materialdesign.mvp.base.IBaseVIew;
import com.example.materialdesign.mvp.bean.ContentBean;

import rx.Observable;

public interface InfoBodyContract {
    interface IInfoBodyModel{

        Observable<ContentBean> onComplete(int infoId);

    }

    interface InfoBodyView extends IBaseVIew{
        void showRequestBody(String date, ContentBean contentBean);
    }
}
