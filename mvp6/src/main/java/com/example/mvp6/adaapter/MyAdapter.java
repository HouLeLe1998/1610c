package com.example.mvp6.adaapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvp6.R;
import com.example.mvp6.bean.Bean;

import java.util.Date;
import java.util.List;

import javax.security.auth.callback.Callback;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<Bean.ResultBean> list;
    Context context;

    public MyAdapter(List<Bean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_shou, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i).getMasterPic()).into(viewHolder.imageView);
        viewHolder.textView.setText(list.get(i).getCommodityName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               recyclerViewListnter.CallBack(list.get(i).getCommodityId());

            }
        });
    }

    //获取条目数量
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.naem);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    RecyclerViewListnter recyclerViewListnter;

    public void setRecyclerViewListnter(RecyclerViewListnter recyclerViewListnter) {
        this.recyclerViewListnter = recyclerViewListnter;
    }

    public interface RecyclerViewListnter {
        void CallBack(int id);
    }
}
