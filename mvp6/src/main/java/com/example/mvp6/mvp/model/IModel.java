package com.example.mvp6.mvp.model;

public interface IModel {
    public void getDate(String url, GetBackCall getBackCall);



    interface GetBackCall {
        public void seccess(String s);

        public void error(Throwable e);
    }
}