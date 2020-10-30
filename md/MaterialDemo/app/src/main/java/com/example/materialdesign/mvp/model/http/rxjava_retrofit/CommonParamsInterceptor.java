package com.example.materialdesign.mvp.model.http.rxjava_retrofit;

import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.util.Log;

import com.example.materialdesign.mvp.utils.ACache;
import com.example.materialdesign.mvp.utils.DeviceUtils;
import com.example.materialdesign.mvp.view.application.AppApplication;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class CommonParamsInterceptor implements Interceptor {

    public Gson gson = new Gson();
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();

        HashMap<String,Object> commomParamsMap =new HashMap<>();
        DeviceUtils deviceUtils = new DeviceUtils(AppApplication.getContext());
        //commomParamsMap.put(Constant.IMEI,deviceUtils.getIMEI());
        commomParamsMap.put(Constant.MODEL,deviceUtils.getSystemModel());
        commomParamsMap.put(Constant.LANGUAGE,deviceUtils.getSystemLanguage());
        commomParamsMap.put(Constant.RESOLUTION,deviceUtils.deviceWidthAndHeight());
        commomParamsMap.put(Constant.SDK,deviceUtils.getSDKVersion());
        commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR,deviceUtils.getDeviceDensity()+"");
        String token = ACache.get(AppApplication.getContext()).getAsString(Constant.TOKEN);
        commomParamsMap.put(Constant.TOKEN,token == null ?"":token);

        if(method.equals("GET")){
            HttpUrl url = request.url();
            Set<String> stringSet = url.queryParameterNames();
            HashMap<String,Object> hashMap = new HashMap<>();
            for (String key : stringSet) {
                if (Constant.PARAM.equals(key)){
                    String s = url.queryParameter(Constant.PARAM);
                    if (s != null){
                        HashMap<String,Object> hashMap1 = gson.fromJson(s, HashMap.class);
                        if (hashMap1 != null){
                            for (HashMap.Entry<String,Object> entry : hashMap1.entrySet()) {
                                hashMap.put(entry.getKey(),entry.getValue());
                            }
                        }
                    }
                }
                else {
                    hashMap.put(key,url.queryParameter(key));
                }
            }
            hashMap.put("publicParams",commomParamsMap);
            String s1 = gson.toJson(hashMap);
            String s2 = url.toString();
            int index = s2.indexOf("?");
            if (index>0){
               s2 =  s2.substring(0,s2.indexOf("?"));
            }
            s2 = s2+"?"+Constant.PARAM+"="+s1;
            Log.e("TAG",s2);
            request = request.newBuilder().url(s2).build();
        }

        else if (method.equals("POST")){
            RequestBody body = request.body();
            Map<String,Object> rootMap = new HashMap<>();
            if (body instanceof FormBody){
                for (int i = 0; i < ((FormBody) body).size(); i++) {
                    rootMap.put(((FormBody) body).encodedName(i),((FormBody) body).encodedValue(i));
                }
            }
            else if (body instanceof RequestBody){
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                String s = buffer.readUtf8();
                if (!TextUtils.isEmpty(s)){
                    //将json数据换转成hashmap值或者字符串
                    rootMap= gson.fromJson(s, HashMap.class);
                    if (rootMap != null){
                        rootMap.put("publicParams",commomParamsMap);
                        String s1 = gson.toJson(rootMap);
                        request = request.newBuilder().post(RequestBody.create(JSON,s1)).build();
                    }
                }

            }

        }

        return chain.proceed(request);
    }
}
