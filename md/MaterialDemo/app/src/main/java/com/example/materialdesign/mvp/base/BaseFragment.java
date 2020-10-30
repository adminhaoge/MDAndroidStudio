package com.example.materialdesign.mvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.materialdesign.R;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.contract.RecommendContract;
import com.example.materialdesign.mvp.di.component.AppComponent;
import com.example.materialdesign.mvp.di.component.DaggerRecommendComponent;
import com.example.materialdesign.mvp.di.module.RecommendModule;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.HttpModel;
import com.example.materialdesign.mvp.presenter.HttpPresenterTitle;
import com.example.materialdesign.mvp.view.application.AppApplication;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment implements RecommendContract.View{
    @Inject
    public HttpPresenterTitle mPresenter;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayout(), container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AppComponent appComponent = ((AppApplication) getActivity().getApplication()).getAppComponent();
        setupActivityComponent(appComponent);
        initData();
        mPresenter.attachView(this);
        mPresenter.fetch();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }


    protected abstract void initData();

    protected abstract int setLayout();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    public void showRequest(List<ResultBean.StoriesBean> beans) {

    }

    @Override
    public void showRequestBody(String date, ContentBean contentBean) {

    }

    @Override
    public void showPostRequestBody(LoginBean loginBean) {

    }
}
