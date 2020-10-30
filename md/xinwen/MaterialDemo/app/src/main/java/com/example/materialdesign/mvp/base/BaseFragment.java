package com.example.materialdesign.mvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.contract.TitleContract;
import com.example.materialdesign.mvp.di.component.AppComponent;
import com.example.materialdesign.mvp.presenter.TitlePresenter;
import com.example.materialdesign.mvp.view.application.AppApplication;

import java.util.List;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment implements TitleContract.titleView {
    @Inject
    public TitlePresenter mPresenter;
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


}
