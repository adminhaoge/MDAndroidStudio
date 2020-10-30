package com.example.imitationtaobao.utils;

import android.os.Handler;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.imitationtaobao.bean.Wares;
import com.example.imitationtaobao.bean.WaresHot;
import com.example.imitationtaobao.bean.WaresPageBean;
import com.example.imitationtaobao.http.MyOkHttp;
import com.example.imitationtaobao.http.ServiceApi;
import com.example.imitationtaobao.http.SubscriberUtils.MySubscriber;
import com.google.android.material.snackbar.BaseTransientBottomBar;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.PUT;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public  class PageUtils {

    private static Builder builder;

    static final int STATE_NORMAL = 0;
    static final int STATE_REFREH = 1;
    static final int STATE_MORE = 2;
    static int state = STATE_NORMAL;

    private PageUtils(Builder builder) {
        this.builder = builder;
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        builder.mRefreshLayout.setLoadMore(builder.canLoadMore);
        builder.mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新监听
                refreshData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                if (builder.currentPageList <= builder.totalCount){
                    loadMoreData();
                }else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getActivity(),"已无数据加载。",Toast.LENGTH_SHORT).show();
                            builder.mRefreshLayout.finishRefreshLoadMore();
                            onfinish();
                        }
                    },2000);
                }
            }
        });
    }

    private void refreshData(){
        builder.currentPage = 1;
        builder.currentPageList = 10;
        state = STATE_REFREH;
        initOkHttp();
    }

    private void loadMoreData(){
        builder.currentPage += 1;
        builder.currentPageList +=10;
        state = STATE_MORE;
        initOkHttp();
    }

    private void initOkHttp() {
        MyOkHttp myOkHttp = new MyOkHttp(builder.url);
        Retrofit retrofit = myOkHttp.setRetrofit();
        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        builder.onServiceApi.Api(serviceApi,builder.currentPage,builder.pageSize);

    }

    public static<T> void RequestData(List<T> list, int totalCount){
        builder.totalCount = totalCount;
        showData(list,totalCount);
    }


    public void request(){
        initOkHttp();
    }

    private static <T> void showData(List<T> data, int totalCount) {
        switch (state){
            case STATE_NORMAL:
                if (builder.onPageListener != null){
                    builder.onPageListener.load(data,totalCount);
                }
                break;
            case STATE_REFREH:
                if (builder.onPageListener != null){
                    builder.onPageListener.refresh(data,totalCount);
                }
                builder.mRefreshLayout.finishRefresh();
                break;
            case STATE_MORE:
                if (builder.onPageListener != null){
                    builder.onPageListener.loadMore(data,totalCount);
                }
                builder.mRefreshLayout.finishRefreshLoadMore();
                break;
        }

    }

    public static Builder newBuilder(){
        return new Builder();
    }


    public static class Builder{
        private String url;
        private HashMap<String,Object> params = new HashMap<>();
        private MaterialRefreshLayout mRefreshLayout;
        private boolean canLoadMore;
        private int currentPage = 1;
        private int pageSize = 10;
        private int currentPageList = 10;
        private int totalCount;
        private OnPageListener onPageListener;
        private OnServiceApi onServiceApi;
        public Builder setUrl(String url){
            this.url = url;
            return this;
        }

        public Builder setParams(String key ,Object value){
            params.put(key,value);
            return this;
        }

        public Builder setRefreshLayout(MaterialRefreshLayout refreshLayout){
            this.mRefreshLayout = refreshLayout;
            return this;
        }

        public Builder setLoadMore(boolean loadMore){
            this.canLoadMore = loadMore;
            return this;
        }
        public Builder setOnPageListener(OnPageListener onPageListener){
            this.onPageListener = onPageListener;
            return this;
        }
        public Builder setOnServiceApi(OnServiceApi onServiceApi){
            this.onServiceApi = onServiceApi;
            return this;
        }

        public PageUtils builder(){
            return new PageUtils(this);
        }



    }

    public interface OnPageListener<T>{
            void load(List<T> data,int totalCount);
            void refresh(List<T> data,int totalCount);
            void loadMore(List<T> data,int totalCount);
    }

    public interface  OnServiceApi{
        void Api(ServiceApi serviceApi,int currentPage, int pageSize);
    }


}


