package com.example.materialdesign.mvp.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.materialdesign.mvp.bean.ContentBean;
import com.example.materialdesign.mvp.bean.LoginBean;
import com.example.materialdesign.mvp.bean.ResultBean;
import com.example.materialdesign.mvp.contract.RecommendContract;
import com.example.materialdesign.mvp.di.component.AppComponent;
import com.example.materialdesign.mvp.di.component.DaggerRecommendComponent;
import com.example.materialdesign.mvp.di.module.RecommendModule;
import com.example.materialdesign.mvp.utils.sharedUtils;
import com.example.materialdesign.R;
import com.example.materialdesign.mvp.view.application.AppApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartupPage extends AppCompatActivity implements RecommendContract.View{

    @BindView(R.id.bt_page)
    Button btPage;
    private Handler handler;
    private Runnable runnable;
    private int count = 3 ;
    @Inject
    public sharedUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_page);
        ButterKnife.bind(this);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        DaggerRecommendComponent.builder().recommendModule(new RecommendModule(this)).appComponent(appComponent).build().inject(this);
        utils.setShared();
        InitIntent();
        btPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                utils.getShared();
                finish();
            }
        });

    }

    private void InitIntent() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
               if (count>0){
                   count --;
                   handler.postDelayed(runnable,1000);
                   btPage.setText("跳转"+count);
               }else{
                   utils.getShared();
                   finish();
               }
            }
        };
        handler.post(runnable);
    }


    @Override
    public void showRequest(List<ResultBean.StoriesBean> beans) {

    }

    @Override
    public void showRequestBody(String date, ContentBean contentBean) {

    }

    @Override
    public void showPostRequestBody(LoginBean loginBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

}