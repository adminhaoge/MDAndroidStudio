package com.example.materialdesign.mvp.view.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.example.materialdesign.R;
import com.example.materialdesign.mvp.base.BaseActivity;
import com.example.materialdesign.mvp.bean.User;
import com.example.materialdesign.mvp.di.component.AppComponent;

import com.example.materialdesign.mvp.utils.Constant;
import com.example.materialdesign.mvp.utils.ACache;
import com.example.materialdesign.mvp.view.activity.login.MainLogin;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.dl_activity_main)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation)
    NavigationView navigation;
    private CircleImageView circleImageView;
    private TextView login;
    private View headerView;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    protected void initData() {
        RxBus.get().register(this);
        InitToolbar_fragment();
        InitBottonInitNavigation();
        ClickInitNav_Head();
        InitNavigation();
    }

    //对mDrawerLayout、toolbar进行合并，并且监听
    private void InitToolbar_fragment() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
    }

    private void InitBottonInitNavigation() {
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    private void ClickInitNav_Head() {
        headerView = navigation.getHeaderView(0);
        circleImageView = headerView.findViewById(R.id.circleImageView);
        login = headerView.findViewById(R.id.mail);
        User asUser = (User) ACache.get(this).getAsObject(Constant.USER);
        if (asUser == null){
            headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplication(),MainLogin.class));
                }
            });
        }else {
            initUserHeadView(asUser);
        }

    }

    private void InitNavigation() {
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.unLogin:
                        unLogin();
                }
                return true;
            }
        });
    }

    private void unLogin() {
        ACache aCache = ACache.get(this);
        aCache.put(Constant.TOKEN,"");
        aCache.put(Constant.USER,"");
        circleImageView.setImageResource(R.drawable.statup);
        login.setText("未登录");
        Toast.makeText(MainActivity.this, "已退出登录", Toast.LENGTH_SHORT).show();
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),MainLogin.class));
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }




    //获取toolbar上对应的可操作按钮进行监听
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(MainActivity.this, "正在上传中...", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void getUser(User user) {
        initUserHeadView(user);
    }

    private void initUserHeadView(User user){
        Glide.with(this).load(user.getLogo_url()).into(circleImageView);
        login.setText(user.getUsername());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }


}