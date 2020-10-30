package com.example.materialdesign.mvp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.materialdesign.mvp.view.activity.GuidePage;
import com.example.materialdesign.mvp.view.activity.MainActivity;
import com.example.materialdesign.mvp.view.activity.login.MainLogin;

public class sharedUtils {
    private static String name = "LoginInfo";
    private static String Checked = "isChecked";
    private Context context;
    private SharedPreferences sharedPreferences;
    private boolean aBoolean;

    public sharedUtils(Context context) {
        this.context = context;
    }

    public void setShared(){
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        //初始化
        aBoolean = sharedPreferences.getBoolean(Checked, true);
    }

    public void getShared(){
        if (aBoolean){
            Intent intent = new Intent(context, GuidePage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            context.startActivity(intent);
            editor.putBoolean(Checked,false);
            editor.apply();
            editor.commit();
        }else {
            Intent intent2 = new Intent(context, MainActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        }
    }
}
