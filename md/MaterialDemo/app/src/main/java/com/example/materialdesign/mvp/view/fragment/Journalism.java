package com.example.materialdesign.mvp.view.fragment;

import android.app.ProgressDialog;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.materialdesign.R;
import com.example.materialdesign.mvp.adapter.MyAdapter;
import com.example.materialdesign.mvp.adapter.MyRecycler;
import com.example.materialdesign.mvp.adapter.OnRecyclerItemClickListener;
import com.example.materialdesign.mvp.base.BaseFragment;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.di.component.AppComponent;

import com.example.materialdesign.mvp.di.component.DaggerRecommendComponent;
import com.example.materialdesign.mvp.di.module.RecommendModule;
import com.example.materialdesign.mvp.model.http.rxjava_retrofit.Constant;
import com.example.materialdesign.mvp.utils.ACache;
import com.example.materialdesign.mvp.view.application.AppApplication;

import java.io.Serializable;
import java.util.List;


public class Journalism extends BaseFragment {

    private SwipeRefreshLayout swipe;
    private RecyclerView Recycler;

    @Override
    protected void initData() {
        swipe = getActivity().findViewById(R.id.swipe);
        Recycler = getActivity().findViewById(R.id.Recycler);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_journalism;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder()
                .recommendModule(new RecommendModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void showRequest(List<ResultBean.StoriesBean> beans) {
        ProgressDialog pd = ProgressDialog.show(getContext(),"加载页面","加载中....");
        pd.setIndeterminate(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MyAdapter myAdapter = new MyAdapter(beans);
                GridLayoutManager layoutParams = new GridLayoutManager(requireActivity(), 1);
                layoutParams.setOrientation(LinearLayoutManager.VERTICAL);
                //将显示格式传给mRecyclerView
                Recycler.setLayoutManager(layoutParams);
                Recycler.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
                pd.dismiss();
            }
        },1000);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

}