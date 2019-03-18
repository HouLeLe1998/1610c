package com.example.mvp6.okhttp;


import android.os.Handler;
import android.util.Log;

import com.example.mvp6.mvp.model.IModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {
    private static OkHttp instance;
    Handler handler = new Handler();

    //    拦截器
    public Interceptor interceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.i("aaa", "Interceptor:拦截前");
                Response proceed = chain.proceed(request);
                Log.i("aaa", "Interceptor:拦截后");
                return proceed;
            }
        };
        return interceptor;
    }
//    创建单例模式

    public static OkHttp getInstance() {
//        单例模式
//        单例模式调用synchronized；
        if (instance == null) {
//            异步
            synchronized (OkHttp.class) {
                if (instance == null) {
                    instance = new OkHttp();
                }
            }
        }
        return instance;
    }

    public OkHttp() {

    }

    //   post 请求
    public void post(String url, String phone, String pwd, final GetCallBack getCallBack) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor()).build();
        FormBody formBody = new FormBody.Builder().add("phone", phone).add("pwd", pwd).build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getCallBack.error(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                           getCallBack.success(string);
                    }
                });
            }
        });
    }

    //get请求数据
    public void get(String url, final GetCallBack getCallBack) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor()).build();
        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getCallBack.error(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getCallBack.success(string);
                    }
                });
            }
        });
    }

    public interface GetCallBack {
        public void success(String  s);

        public void error(Throwable e);
    }
}
