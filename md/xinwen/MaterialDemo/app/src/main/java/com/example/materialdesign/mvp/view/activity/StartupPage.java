package com.example.materialdesign.mvp.view.activity;

import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.materialdesign.mvp.base.BaseActivity;
import com.example.materialdesign.mvp.di.component.AppComponent;
import com.example.materialdesign.mvp.di.component.DaggerUtilsComponent;
import com.example.materialdesign.mvp.di.module.UtilsModule;
import com.example.materialdesign.mvp.utils.sharedUtils;
import com.example.materialdesign.R;

import javax.inject.Inject;

import butterknife.BindView;

public class StartupPage extends BaseActivity {

    @BindView(R.id.bt_page)
    Button btPage;
    private Handler handler;
    private Runnable runnable;
    private int count = 3 ;
    @Inject
    public sharedUtils utils;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerUtilsComponent
                .builder()
                .appComponent(appComponent)
                .utilsModule(new UtilsModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


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

    @Override
    protected int setLayout() {
        return R.layout.activity_startup_page;
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

}