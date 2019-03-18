package com.example.mvp6.mvp.model;

import com.example.mvp6.okhttp.OkHttp;

public class Model  implements IModel{


    @Override
    public void getDate(String url, final GetBackCall getBackCall) {
       OkHttp.getInstance().get(url, new OkHttp.GetCallBack() {
           @Override
           public void success(String s) {
               getBackCall.seccess(s);
           }

           @Override
           public void error(Throwable e) {
               getBackCall.error(e);
           }
       });
    }
}
