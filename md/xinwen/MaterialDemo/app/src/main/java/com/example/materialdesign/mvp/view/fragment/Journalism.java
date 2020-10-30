package com.example.materialdesign.mvp.view.fragment;

import android.app.ProgressDialog;
import android.os.Handler;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.materialdesign.R;
import com.example.materialdesign.mvp.adapter.MyAdapter;
import com.example.materialdesign.mvp.base.BaseFragment;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.contract.TitleContract;
import com.example.materialdesign.mvp.di.component.AppComponent;

import com.example.materialdesign.mvp.di.component.DaggerTitleComponent;
import com.example.materialdesign.mvp.di.module.LoginModule;
import com.example.materialdesign.mvp.di.module.TitleModule;

import java.util.List;


public class Journalism extends BaseFragment  {

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
        DaggerTitleComponent.builder()
                .titleModule(new TitleModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void showRequest(List<ResultBean.StoriesBean> beans) {
        ProgressDialog pd = ProgressDialog.show(getContext(),"加载页面","加载中....");
        pd.setIndeterminate(false);
                MyAdapter myAdapter = new MyAdapter(beans);
                GridLayoutManager layoutParams = new GridLayoutManager(requireActivity(), 1);
                layoutParams.setOrientation(LinearLayoutManager.VERTICAL);
                //将显示格式传给mRecyclerView
                Recycler.setLayoutManager(layoutParams);
                Recycler.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
                pd.dismiss();
            }
    }
