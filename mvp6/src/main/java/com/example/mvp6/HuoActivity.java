package com.example.mvp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mvp6.mvp.Presenter.Presenter;
import com.example.mvp6.mvp.view.IView;
import com.example.mvp6.okhttp.OkHttp;

public class HuoActivity extends AppCompatActivity implements IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huo);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

    }

    @Override
    public void loab(String date) {

    }
}
