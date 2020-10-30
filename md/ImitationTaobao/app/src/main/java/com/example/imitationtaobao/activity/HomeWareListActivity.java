package com.example.imitationtaobao.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.example.imitationtaobao.R;
import com.example.imitationtaobao.adapter.MyWaresHotAdapter;
import com.example.imitationtaobao.adapter.MyWaresListAdapter;
import com.example.imitationtaobao.bean.ListBean;
import com.example.imitationtaobao.bean.Wares;
import com.example.imitationtaobao.http.ServiceApi;
import com.example.imitationtaobao.http.SubscriberUtils.MySubscriber;
import com.example.imitationtaobao.mToolbar;
import com.example.imitationtaobao.utils.Constant;
import com.example.imitationtaobao.utils.PageUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeWareListActivity extends AppCompatActivity implements  PageUtils.OnPageListener<ListBean>,TabLayout.OnTabSelectedListener,View.OnClickListener{
    @BindView(R.id.toolbar)
    mToolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.refresh_layout)
    MaterialRefreshLayout refreshLayout;
    @BindView(R.id.txt_totalCount)
    TextView txtTotalCount;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private long campaignId;
    private int orderBy = 0;
    private MyWaresListAdapter myWaresListAdapter;
    private Map<String, Object> params;
    private PageUtils builder;

    public static final int ACTION_LIST = 1;
    public static final int ACTION_GRID = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_warelist);
        ButterKnife.bind(this);
        campaignId = getIntent().getIntExtra(Constant.COMPAINGAIN, 0);
        initTab();
        getData();
        initToolBar();
    }

    public void initToolBar(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setRightButtonIcon(R.drawable.ic_grid);
        toolbar.getRightButton().setTag(ACTION_LIST);

        toolbar.getRightButton().setOnClickListener(this);

    }

    private void getData() {
        builder = PageUtils.newBuilder()
                .setUrl("http://112.124.22.238:8081/")
                .setRefreshLayout(refreshLayout)
                .setLoadMore(true)
                .setOnServiceApi(new PageUtils.OnServiceApi() {
                    @Override
                    public void Api(ServiceApi serviceApi, int currentPage, int pageSize) {
                        params = new HashMap<>();
                        params.put("curPage", currentPage);
                        params.put("pageSize", pageSize);
                        params.put("orderBy", orderBy);
                        params.put("campaignId", campaignId);
                        serviceApi.setClassWaresActivity(params)
                                .subscribeOn(Schedulers.io())
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new MySubscriber<Wares<ListBean>>() {
                                    @Override
                                    public void onNext(Wares<ListBean> listBeanWares) {
                                        int totalCount = listBeanWares.getTotalCount();
                                        PageUtils.RequestData(listBeanWares.getList(), totalCount);
                                    }
                                });
                    }
                })
                .setOnPageListener(this)
                .builder();
        builder.request();
    }

    private void initTab() {
        TabLayout.Tab tab = tabLayout.newTab();

        tab.setText(R.string.defaults);
        tab.setTag(0);
        tabLayout.addTab(tab);

        tab = tabLayout.newTab();
        tab.setText(R.string.Price);
        tab.setTag(1);
        tabLayout.addTab(tab);

        tab = tabLayout.newTab();
        tab.setText(R.string.salesVolume);
        tab.setTag(2);
        tabLayout.addTab(tab);

        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void load(List<ListBean> data, int totalCount) {
        txtTotalCount.setText("共有"+totalCount+"件商品");
        myWaresListAdapter = new MyWaresListAdapter(data);
        GridLayoutManager manager = new GridLayoutManager(getApplication(), 1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myWaresListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void refresh(List<ListBean> data, int totalCount) {
        myWaresListAdapter.clearData();
        myWaresListAdapter.addData(data);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void loadMore(List<ListBean> data, int totalCount) {
        myWaresListAdapter.addData(data.size(), data);
        recyclerView.scrollBy(data.size(), data.size() * 2);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        orderBy = (int) tab.getTag();
        params.put("orderBy", orderBy);
        builder.request();
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        int action = (int) v.getTag();
        if (ACTION_LIST == action){
            toolbar.getRightButton().setTag(ACTION_GRID);
            toolbar.setRightButtonIcon(R.drawable.ic_list);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }else if (ACTION_GRID == action){
            toolbar.setRightButtonIcon(R.drawable.ic_grid);
            toolbar.getRightButton().setTag(ACTION_LIST);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
