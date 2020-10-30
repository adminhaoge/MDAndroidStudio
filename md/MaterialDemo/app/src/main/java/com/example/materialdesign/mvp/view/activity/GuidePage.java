package com.example.materialdesign.mvp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.materialdesign.R;
import com.example.materialdesign.mvp.adapter.MyJazzyViewPager;
import com.example.materialdesign.mvp.adapter.ViewPagerAdapter;
import com.example.materialdesign.mvp.view.fragment.startuppage.intelligence;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuidePage extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.bt_main)
    Button btMain;
    private List<Fragment> mFragments;
    private ImageView[] dots;
    private int[] ids = {R.id.dot_01, R.id.dot_02, R.id.dot_03};
    private ViewPagerAdapter viewPagerAdapter;
    private MyJazzyViewPager mViewPager;
    int currount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Page_Adaper();
        initDots();
    }

    private void initDots() {
        dots = new ImageView[mFragments.size()];

        for (int i = 0; i < mFragments.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }

        dots[0].setImageResource(R.drawable.ic_sharp_brightness_1_24_select);
    }

    private void Page_Adaper() {
        mFragments = new ArrayList<>();
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments);
        mFragments.add(intelligence.newInstance(R.drawable.page1));
        mFragments.add(intelligence.newInstance(R.drawable.page2));
        mFragments.add(intelligence.newInstance(R.drawable.page3));
        mViewPager = (MyJazzyViewPager) findViewById(R.id.page_view);
        mViewPager.setPageTransformer(true,new DepthPageTransformer());
        mViewPager.setCurrentItem(currount);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    //当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    // 此方法是页面跳转完后得到调用
    public void onPageSelected(int position) {
        currount = position;
        for (int i = 0; i < ids.length; i++) {
            if (position == i) {
                dots[i].setImageResource(R.drawable.ic_sharp_brightness_1_24_select);
            } else {
                dots[i].setImageResource(R.drawable.ic_sharp_brightness_1_24);

            }
        }
            btMain.setVisibility(position == viewPagerAdapter.getCount()-1 ? View.VISIBLE:View.GONE);
            btMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplication(), MainActivity.class));
                }
            });
    }

    @Override
    //此方法是在状态改变的时候调用
    public void onPageScrollStateChanged(int i) {

    }

}
