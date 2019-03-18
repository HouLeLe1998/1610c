package com.example.mvp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mvp6.adaapter.MyAdapter;
import com.example.mvp6.bean.Bean;
import com.example.mvp6.mvp.Presenter.Presenter;
import com.example.mvp6.mvp.view.IView;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IView, MyAdapter.RecyclerViewListnter {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Presenter presenter = new Presenter(this);
//        获取接口
        presenter.getDatModel("http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=高跟鞋&page=1&count=7");
//
        InitView();
    }

    private void InitView() {
        recyclerView = findViewById(R.id.RecyclerView);
//        网格模式
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void loab(String date) {
        Gson gson = new Gson();
        Bean bean = gson.fromJson(date, Bean.class);
        List<Bean.ResultBean> result = bean.getResult();
        MyAdapter myAdapter = new MyAdapter(result, MainActivity.this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setRecyclerViewListnter(this);

    }

    @Override
    public void CallBack(int id) {
         Intent intent = new Intent(MainActivity.this,HuoActivity.class);
         intent.putExtra("id",id);
         startActivity(intent);
    }
}
