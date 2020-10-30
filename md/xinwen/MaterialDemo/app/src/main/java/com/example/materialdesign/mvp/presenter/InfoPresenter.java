package com.example.materialdesign.mvp.presenter;

import com.example.materialdesign.mvp.base.BasePresenter;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.contract.InfoBodyContract;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InfoPresenter extends BasePresenter<InfoBodyContract.IInfoBodyModel, InfoBodyContract.InfoBodyView> {
    @Inject
    public InfoPresenter(InfoBodyContract.IInfoBodyModel mModule, InfoBodyContract.InfoBodyView mView) {
        super(mModule, mView);
    }


    public void fetch(int infoId) {
        if (mModule != null && mView != null){
            mModule.onComplete(infoId).subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ContentBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(ContentBean contentBean) {
                            String body = contentBean.getBody();
                            mView.showRequestBody(body,contentBean);
                        }
                    });
        }
    }


}
