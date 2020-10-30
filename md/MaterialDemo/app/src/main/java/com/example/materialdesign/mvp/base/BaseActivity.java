package com.example.materialdesign.mvp.base;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.materialdesign.R;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.contract.RecommendContract;
import com.example.materialdesign.mvp.di.component.AppComponent;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.HttpModel;
import com.example.materialdesign.mvp.presenter.HttpPresenterBody;
import com.example.materialdesign.mvp.presenter.PostHttpPresenterBody;
import com.example.materialdesign.mvp.view.application.AppApplication;
import com.hwangjr.rxbus.RxBus;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements RecommendContract.View {
    @Inject
    HttpPresenterBody mPresenter;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        unbinder = ButterKnife.bind(this);
        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        setupActivityComponent(appComponent);
        initData();
        mPresenter.attachView(this);
        mPresenter.fetch();
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
        mPresenter.detachView();
    }

    @Override
    public void showRequest(List<ResultBean.StoriesBean> beans) {

    }

    @Override
    public void showRequestBody(String date, ContentBean contentBean) {

    }

    @Override
    public void showPostRequestBody(LoginBean loginBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
