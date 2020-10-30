package com.example.imitationtaobao.fragment;

import android.animation.ObjectAnimator;
import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.example.imitationtaobao.R;
import com.example.imitationtaobao.adapter.MyWaresHotAdapter;
import com.example.imitationtaobao.base.BaseFragment;
import com.example.imitationtaobao.bean.WaresHot;
import com.example.imitationtaobao.bean.WaresPageBean;
import com.example.imitationtaobao.http.ServiceApi;
import com.example.imitationtaobao.http.SubscriberUtils.MySubscriber;
import com.example.imitationtaobao.utils.PageUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class chartFragment extends BaseFragment implements PageUtils.OnPageListener<WaresHot> {

    @BindView(R.id.recycler)
    RecyclerView Recycler;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private MyWaresHotAdapter myWaresHotAdapter;


    @Override
    protected int LayoutInit() {
        return R.layout.chart_fragment;

    }

    @Override
    protected void initLogic() {
        PageUtils builder = PageUtils.newBuilder()
                .setUrl("http://112.124.22.238:8081/")
                .setRefreshLayout(refresh)
                .setLoadMore(true)
                .setOnServiceApi(new PageUtils.OnServiceApi() {
                    @Override
                    public void Api(ServiceApi serviceApi, int currentPage, int pageSize) {
                        Map<String,Integer> params = new HashMap<>();
                        params.put("curPage",currentPage);
                        params.put("pageSize",pageSize);
                        serviceApi.setWares(params)
                                .subscribeOn(Schedulers.io())
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new MySubscriber<WaresPageBean<WaresHot>>() {
                                    @Override
                                    public void onNext(WaresPageBean<WaresHot> PageBean) {
                                        int totalCount = PageBean.getTotalCount();
                                        PageUtils.RequestData(PageBean.getList(),totalCount);
                                    }
                                });
                    }
                })
                .setOnPageListener(this).builder();
        builder.request();


    }


    @Override
    public void load(List<WaresHot> data, int totalCount) {
        myWaresHotAdapter = new MyWaresHotAdapter(data);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        Recycler.setLayoutManager(manager);
        Recycler.setAdapter(myWaresHotAdapter);
        Recycler.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void refresh(List<WaresHot> data, int totalCount) {
        myWaresHotAdapter.clearData();
        myWaresHotAdapter.addData(data);
        Recycler.scrollToPosition(0);
    }

    @Override
    public void loadMore(List<WaresHot> data, int totalCount) {
        myWaresHotAdapter.addData(data.size(), data);
        Recycler.scrollBy(data.size(), data.size() * 2);
    }
}