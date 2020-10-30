package com.example.materialdesign.mvp.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.materialdesign.mvp.view.application.AppApplication;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private static final String REQUEST_PATH_GET = "https://news-at.zhihu.com/";
    private static final String REQUEST_PATH_POST = "http://112.124.22.238:8081/";
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        HttpUrl oldHttpUrl = request.url();
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers("urlname");
        if(headerValues != null && headerValues.size() >0){
            builder.removeHeader("urlname");
            //匹配新的url
            String headerValue = headerValues.get(0);
            HttpUrl newBaseUrl = null;
            if ("test1".equals(headerValue)){
                newBaseUrl = HttpUrl.parse(REQUEST_PATH_GET);
            }else if ("test2".equals(headerValue)){
                newBaseUrl = HttpUrl.parse(REQUEST_PATH_GET);
            }else if ("test3".equals(headerValue)){
                newBaseUrl = HttpUrl.parse(REQUEST_PATH_POST);
            }else {
                newBaseUrl = oldHttpUrl;
            }

            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme(newBaseUrl.scheme())
                    .host(newBaseUrl.host())
                    .port(newBaseUrl.port())
                    .build();
            Log.e("Url", "intercept: "+newFullUrl.toString());
            return chain.proceed(builder.url(newFullUrl).build());
        }











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
                        Log.e("TAG",s1);
                        request = request.newBuilder().post(RequestBody.create(JSON,s1)).build();
                    }
                }

            }

        }

        return chain.proceed(request);
    }
}
