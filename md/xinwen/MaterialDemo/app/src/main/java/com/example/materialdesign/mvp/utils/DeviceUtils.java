package com.example.materialdesign.mvp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.RemoteException;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresPermission;

import java.util.Locale;

/**
 * 此工具类作用是获取手机上基本信息的使用
 */

public class DeviceUtils {
    private  Context context;
    private  String[] phoneType = {"WIFI","2G","3G","4G"};
    private TelephonyManager tManager;

    public DeviceUtils(Context context) {
        this.context = context;
        initManager();
    }

    private void initManager() {
        tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    }


    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     * return 手机IMEI
     */
    @SuppressLint({"MissingPermission", "NewApi"})
    public  String getIMEI(){
        if (tManager != null){
            return tManager.getImei();
        }
        return  null;
    }


    /**
     * 获取手机当前网络类型（2G,3G,4G,WIFI）
     * @return 返回网络类型
     */
    public String getNetWork(){
        String NetWorkType;
        NetWorkType = phoneType[tManager.getPhoneType()];
        return NetWorkType;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机名称
     *
     * @return  手机名称
     */
    public String getDeviceBrand() {
        return android.os.Build.BRAND;
    }



    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public  String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取设备sdk版本号
     *
     * @return 设备sdk版本号
     */
    public int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }


    /**
     * 获取设备宽度和高度（px）
     * @return 返回分辨率格（1080*1920）
     */
    public String deviceWidthAndHeight() {
        String device;
        int Width = context.getResources().getDisplayMetrics().widthPixels;
        int Height = context.getResources().getDisplayMetrics().heightPixels;
        return device = (""+Width+""+"*"+""+Height+"");
    }


    /**
     * 获取设备屏幕密度,像素的比例
     * @return
     */
    public float getDeviceDensity(){
        return context.getResources().getDisplayMetrics().density;
    }



}
