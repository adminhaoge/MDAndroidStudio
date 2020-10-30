package com.example.materialdesign.mvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.materialdesign.mvp.view.application.AppApplication;

public class NetUtils {
    public static boolean checkNetWork(){
        try{
            ConnectivityManager connectactivity = (ConnectivityManager) AppApplication.getContext().
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectactivity != null){
//                获知网络管理的对象
                NetworkInfo info = connectactivity.getActiveNetworkInfo();
//                判断当前网络是否已经连接
                if(info.getState() == NetworkInfo.State.CONNECTED){
                    return true ;
                }
            }
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        return false ;
    }
}
