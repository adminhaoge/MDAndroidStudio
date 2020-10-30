package com.example.imitationtaobao.fragment;

import android.os.Handler;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.imitationtaobao.R;
import com.example.imitationtaobao.adapter.MyLeftAdapter;
import com.example.imitationtaobao.adapter.SliderAndRecycleAdapter;
import com.example.imitationtaobao.base.BaseFragment;
import com.example.imitationtaobao.bean.ClassLeftBean;
import com.example.imitationtaobao.bean.ClassWares;
import com.example.imitationtaobao.bean.ClassWaresPageBean;
import com.example.imitationtaobao.bean.SlideImage;
import com.example.imitationtaobao.http.MyOkHttp;
import com.example.imitationtaobao.http.ServiceApi;
import com.example.imitationtaobao.http.SubscriberUtils.MySubscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ClassFragment extends BaseFragment {


    @BindView(R.id.recyclerview_category)
    RecyclerView recycler;
    @BindView(R.id.recycler_class)
    RecyclerView recyclerClass;
    @BindView(R.id.RefreshLayout)
    MaterialRefreshLayout RefreshLayout;

    private ServiceApi serviceApi;
    private MyOkHttp myOkHttp;

    private int currentPage = 1;
    private int pageSize = 10;
    int currentPageList = 10;
    int total = 0;

    static final int STATE_NORMAL = 0;
    static final int STATE_REFREH = 1;
    static final int STATE_MORE = 2;
    private int state = STATE_NORMAL;

    private SliderAndRecycleAdapter sliderAndRecycleAdapter;
    private MyLeftAdapter classAdapter;
    private int pageId;

    @Override
    protected int LayoutInit() {
        return R.layout.class_fragment;
    }

    @Override
    protected void initLogic() {
        initOkHttp();
        OkHttpClassBean();
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        RefreshLayout.setLoadMore(true);
        RefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新监听
                refreshData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                if (currentPageList <= total) {
                    loadMoreData();
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(materialRefreshLayout.getContext(), "已无数据加载。", Toast.LENGTH_SHORT).show();
                            RefreshLayout.finishRefreshLoadMore();
                            onfinish();
                        }
                    }, 2000);
                }
            }
        });
    }

    private  void refreshData(){
        currentPageList = 10;
        state = STATE_REFREH;
        ClickClassGory(pageId);
    }

    private void loadMoreData(){
        currentPageList +=10;
        state = STATE_MORE;
        ClickClassGory(pageId);
    }


    private void initOkHttp() {
        myOkHttp = new MyOkHttp("http://112.124.22.238:8081/");
        Retrofit retrofit = myOkHttp.setRetrofit();
        serviceApi = retrofit.create(ServiceApi.class);
    }


    private void OkHttpClassBean() {
        Observable<List<ClassLeftBean>> listObservable = serviceApi.setClass();
        listObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new MySubscriber<List<ClassLeftBean>>() {
                    @Override
                    public void onNext(List<ClassLeftBean> classBeans) {
                        initClassUi(classBeans);

                    }
                });

    }

    private void initClassUi(List<ClassLeftBean> classBeans) {
            classAdapter = new MyLeftAdapter(classBeans);
            ClickClassGory(1);
            classAdapter.setListener(new MyLeftAdapter.OnClassBeanClick() {
                @Override
                public void setClick(ClassLeftBean classBean) {
                    currentPage = 1;
                    state = STATE_NORMAL;
                    pageId = classBean.getId();
                    ClickClassGory(classBean.getId());
                }
            });
            recycler.setAdapter(classAdapter);
            GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
            recycler.setLayoutManager(manager);
            recycler.setItemAnimator(new DefaultItemAnimator());


    }


    private void ClickClassGory(int getId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("curPage", currentPage);
        params.put("pageSize", currentPageList);
        params.put("categoryId", getId);
        Observable<ClassWaresPageBean<ClassWares>> classWares = serviceApi.setClassWares(params);
        classWares
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new MySubscriber<ClassWaresPageBean<ClassWares>>() {
                    @Override
                    public void onNext(ClassWaresPageBean<ClassWares> classWares) {
                        total=classWares.getTotalCount();
                        List<ClassWares> list = classWares.getList();
                        OkHttpSlidelBean(list);
                    }
                });
    }

    private void OkHttpSlidelBean(List<ClassWares> list) {
        Observable<List<SlideImage>> listObservable = serviceApi.setSlide(1);
        listObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new MySubscriber<List<SlideImage>>() {
                    @Override
                    public void onNext(List<SlideImage> slideImages) {
                        showData(slideImages, list);
                    }
                });
    }

    private void showData(List<SlideImage> slideImages, List<ClassWares> list) {
        switch (state) {
            case STATE_NORMAL:
                if (sliderAndRecycleAdapter == null){
                    sliderAndRecycleAdapter = new SliderAndRecycleAdapter(list, slideImages);
                    recyclerClass.setAdapter(sliderAndRecycleAdapter);
                    GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
                    recyclerClass.setLayoutManager(manager);
                    recyclerClass.setItemAnimator(new DefaultItemAnimator());
                    recyclerClass.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                }else {
                    sliderAndRecycleAdapter.clearData();
                    sliderAndRecycleAdapter.addDataWares(list);
                }
                break;
            case STATE_REFREH:
                sliderAndRecycleAdapter.clearData();
                sliderAndRecycleAdapter.addDataWares(list);
                recyclerClass.scrollToPosition(0);
                RefreshLayout.finishRefresh();
                break;
            case STATE_MORE:
                sliderAndRecycleAdapter.addDataWares(list.size(), list);
                recyclerClass.scrollBy(list.size(), list.size() * 2);
                RefreshLayout.finishRefreshLoadMore();
                break;
        }

    }


}