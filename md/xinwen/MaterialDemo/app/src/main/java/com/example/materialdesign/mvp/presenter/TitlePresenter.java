package com.example.materialdesign.mvp.presenter;

import com.example.materialdesign.mvp.base.BasePresenter;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.contract.TitleContract;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TitlePresenter extends BasePresenter<TitleContract.ITitleModel,TitleContract.titleView> {

    @Inject
    public TitlePresenter(TitleContract.ITitleModel mModule, TitleContract.titleView mView) {
        super(mModule, mView);
    }

    public void fetch() {
        if (mModule != null && mView != null){
            mModule.getPathData().subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ResultBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(ResultBean resultBean) {
                            mView.showRequest(resultBean.getStories());
                        }
                    });

        }
    }


}
