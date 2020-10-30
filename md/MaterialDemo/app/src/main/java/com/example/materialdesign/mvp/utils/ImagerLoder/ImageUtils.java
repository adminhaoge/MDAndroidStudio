package com.example.materialdesign.mvp.utils.ImagerLoder;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImageUtils {

    /**
     *创建缓存目录
     * @param context 上下文
     * @param uniqueName
     * @return 缓存目录/缓存名
     */
    public static File getLruCacheDir(Context context,String uniqueName){

        try {
                String cachePath;
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                        || !Environment.isExternalStorageRemovable()){
                    cachePath = context.getExternalCacheDir().getPath();
                }else {
                    cachePath = context.getCacheDir().getPath();
                }
                return new File(cachePath + File.separator +uniqueName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *获取APP版本号
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context){
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


    /**
     *工具类
     * @param key 用于将图片的URL转换为MD5编码后的字符串,用作缓存文件的key进行存储，保证其独一性
     * @return
     */

    public static String hasKeyForCache(String key){
        String cacheKey;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(key.getBytes());
            cacheKey = bytesToHexString(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] digest) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ;i < digest.length ; i++){
            String hex = Integer.toHexString(0xFF & digest[i]);
            if (hex.length() == 1){
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
