package com.jack.carebaby.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jack.carebaby.R;
import com.jack.carebaby.utils.BillAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bgbsk.babycare.global.Data;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BillActivity extends Activity {
    String url = Data.getUrl();
    String phone = Data.getPhone();
    private static final int COMPLETED = 0;
    private FloatingActionButton fab;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        fab = findViewById(R.id.fab);
        Get();
    }

    int request_Code = 1;

    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.back:
                BillActivity.this.finish();
                break;
            // 悬浮按钮，新增账单
            case R.id.fab:
                startActivityForResult(new Intent("com.liuwan.demo.MainActivity"),request_Code);
                break;
            // 刷新页面
            case R.id.refresh:
                Get();
                break;
        }
    }

    // 获取数据库信息
    public void Get(){
        final List<String> id = new ArrayList<>();
        final List<String> title = new ArrayList<>();
        final List<String> date = new ArrayList<>();
        final List<String> price = new ArrayList<>();
        Log.d("Upload","click");
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url+"/bill/get?phone="+phone).build();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == COMPLETED) {
                    init_ada(id,title,date,price); //UI更改操作
                }
            }
        };
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("GetError", e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                JSONObject jsonObject = JSON.parseObject(response.body().string());
                JSONArray jsonArray = jsonObject.getJSONArray("bill");
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject0 = jsonArray.getJSONObject(i);
                    id.add(jsonObject0.getString("_id"));
                    title.add(jsonObject0.getString("title"));
                    date.add(jsonObject0.getString("date"));
                    price.add(jsonObject0.getString("price"));
                }
                Message message = new Message();
                message.what = COMPLETED;
                handler.sendMessage(message);
            }
        });
    }

    public void init_ada(List<String> idList,List<String> titleList,List<String> dateList,List<String> priceList){
        BillAdapter billadpapter;
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(BillActivity.this, 1);
        recyclerView.setLayoutManager(layoutManager);
        billadpapter = new BillAdapter(idList,titleList,dateList,priceList);
        recyclerView.setAdapter(billadpapter);
    }
}
