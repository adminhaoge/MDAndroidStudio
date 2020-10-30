package com.example.materialdesign.mvp.view.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.materialdesign.mvp.utils.DeviceUtils;
import com.example.materialdesign.mvp.view.activity.StartupPage;

public class PermissionActivity extends AppCompatActivity {

    //标识code
    public static final int READ_PHONE_STATE_CODE = 1024;
    public String[] ManPermission = {Manifest.permission.READ_PHONE_STATE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
            ,Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //判断清单文件里面的权限是否成功开启（PERMISSION_GRANTED:0代表成功 ,PERMISSION_DENIED:-1代表失败）
        if (ActivityCompat.checkSelfPermission(this,ManPermission.toString() ) != PackageManager.PERMISSION_GRANTED){
            //未授权，提示授权
                requestPermissions(ManPermission,READ_PHONE_STATE_CODE);
            }else {
            //已经授权
            startActivity(new Intent(this,StartupPage.class));
           }
        }
    }

    //该方法用户判断用户点击了接受还是拒绝，如果接收则进入引导界面，如果拒绝直接退出程序
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //判断标识code是否等于自己设置的code
        if (requestCode == READ_PHONE_STATE_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(new Intent(this,StartupPage.class));
            }else {
                Log.e("TAG","用户拒绝授权");
                finish();
            }
        }
    }
}
