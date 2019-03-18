package com.example.mvp6.mvp.Presenter;

import com.example.mvp6.HuoActivity;
import com.example.mvp6.MainActivity;
import com.example.mvp6.mvp.model.IModel;
import com.example.mvp6.mvp.model.Model;

public class Presenter implements IPresenter {
    private MainActivity mainActivity;
    private HuoActivity huoActivity;
    private final Model model;

    public Presenter(MainActivity mainActivity, HuoActivity huoActivity) {
        this.mainActivity = mainActivity;
        this.huoActivity = huoActivity;
        model = new Model();
    }
    

    //       引用IModel层的接口
    @Override
    public void getDatModel(String url) {
        model.getDate(url, new IModel.GetBackCall() {
            @Override
            public void seccess(String s) {
                mainActivity.loab(s);
            }

            @Override
            public void error(Throwable e) {
                mainActivity.loab(e.getMessage());
            }
        });
    }

}
