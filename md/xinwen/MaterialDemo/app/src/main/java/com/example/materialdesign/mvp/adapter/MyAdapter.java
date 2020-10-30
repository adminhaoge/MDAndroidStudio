package com.example.materialdesign.mvp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.materialdesign.R;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.customview.SlideShowView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ResultBean.StoriesBean> beanList;

    public MyAdapter(List<ResultBean.StoriesBean> beanList) {
        this.beanList = beanList;
    }

    public static final int TYPE_BANNER = 1;
    public static final int TYPE_JOURNALISM = 2;

    //适用于多个布局的使用
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_JOURNALISM;
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_slideshowview, parent, false);
            return new MyBannerHolder(view);
        } else if (viewType == TYPE_JOURNALISM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_apprecyclerview, null, false);
            return new MyViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (position == 0) {
            MyBannerHolder myBannerHolder = (MyBannerHolder) holder;
            myBannerHolder.slideshowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(),"你正在点击此图片",Toast.LENGTH_SHORT).show();
                }
            });

        } else if (position == 1) {
            MyRecycler myRecycler = new MyRecycler(beanList);
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.Recycler.setAdapter(myRecycler);
            GridLayoutManager layoutParams = new GridLayoutManager(holder.itemView.getContext(), 1);
            //将显示格式传给mRecyclerView
            myViewHolder.Recycler.setLayoutManager(layoutParams);
            myRecycler.notifyDataSetChanged();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class MyBannerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.slideshowView)
        SlideShowView slideshowView;
        public MyBannerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Recycler)
        RecyclerView Recycler;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

}
