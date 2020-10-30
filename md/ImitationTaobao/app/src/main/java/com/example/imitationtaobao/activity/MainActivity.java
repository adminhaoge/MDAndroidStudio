package com.example.imitationtaobao.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.imitationtaobao.R;
import com.example.imitationtaobao.mToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNav)
    BottomNavigationView bottomNav;
    @BindView(R.id.toolbar_activity_info)
    mToolbar toolbarActivityInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmenttabhost);
        ButterKnife.bind(this);
        InitBottonInitNavigation();

    }


    private void InitBottonInitNavigation() {
        setSupportActionBar(toolbarActivityInfo);
        //当大于三个底部bottom大于三个图标时底部文字是不会显示的，必须加上这个代码
        bottomNav.setLabelVisibilityMode(1);
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId()!=R.id.shopping){
                    toolbarActivityInfo.showSearchView();
                    toolbarActivityInfo.hideTitleView();
                    toolbarActivityInfo.getRightButton().setVisibility(View.GONE);

                }
            }
        });
        AppBarConfiguration config = new AppBarConfiguration.Builder(bottomNav.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,config);
        NavigationUI.setupWithNavController(bottomNav,navController);
    }



}