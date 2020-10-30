package com.example.materialdesign.mvp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.materialdesign.R;
import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.contract.RecommendContract;
import com.example.materialdesign.mvp.di.component.AppComponent;
import com.example.materialdesign.mvp.presenter.HttpPresenterTitle;
import com.example.materialdesign.mvp.view.application.AppApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class ProgressFragment extends Fragment implements RecommendContract.View{
    @Inject
    public HttpPresenterTitle mPresenter;
    private FrameLayout rootView;
    private View view_progress;
    private FrameLayout view_content;
    private View view_empty;
    private Unbinder bind;
    private TextView mTextError;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress,container,false);
        view_progress = rootView.findViewById(R.id.view_progress);
        view_content = rootView.findViewById(R.id.view_content);
        view_empty = rootView.findViewById(R.id.view_empty);
        mTextError = rootView.findViewById(R.id.text_tip);

        view_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickEmpty();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AppComponent appComponent = ((AppApplication) getActivity().getApplication()).getAppComponent();
        setupActivityComponent(appComponent);
        setRealContentView();
        initData();
        mPresenter.attachView(this);
        mPresenter.fetch();

    }

    public void onClickEmpty(){

    }

    private void setRealContentView() {
        View inflate = LayoutInflater.from(getActivity()).inflate(setLayout(), view_content, true);
        bind = ButterKnife.bind(this, inflate);

    }


    public void showProgressView(){
        showView(R.id.view_progress);
    }

    public void showContentView(){
        showView(R.id.view_content);
    }

    public void  showEmptyView(){
        showView(R.id.view_empty);
    }
    public void  showEmptyTextInt(int resId){
        showEmptyView();
        mTextError.setText(resId);
    }

    public void  showEmptyTextStr(String msg){
        showEmptyView();
        mTextError.setText(msg);
    }


    public void showView(int viewId){
        for (int i = 0; i < rootView.getChildCount(); i++) {
            if (rootView.getChildAt(i).getId() == viewId){
                 rootView.getChildAt(i).setVisibility(View.VISIBLE);
            }else {
                 rootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != bind.EMPTY){
            bind.unbind();
        }
        mPresenter.detachView();
    }

    protected abstract int setLayout();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected abstract void initData();

    @Override
    public void showRequest(List<ResultBean.StoriesBean> beans) {

    }

    @Override
    public void showRequestBody(String date, ContentBean contentBean) {

    }


}
