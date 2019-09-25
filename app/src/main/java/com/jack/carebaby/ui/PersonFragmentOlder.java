package com.jack.carebaby.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jack.carebaby.R;
import com.jack.carebaby.base.BaseFragment;
import com.jack.carebaby.utils.OlderAdapter;

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

import static cn.bgbsk.babycare.global.Data.getLoginStatus;
import static cn.bgbsk.babycare.global.Data.getUsername;

public class PersonFragmentOlder extends BaseFragment{


    private TextView Login;
    private ImageView EditName;
    private LinearLayout Setting;
    private LinearLayout More;
    private LinearLayout Help;
    private LinearLayout Know;
    private LinearLayout NewOlder;

    private static final int COMPLETED = 0;
    private RecyclerView olderlist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume(){
        super.onResume();
        String phone = Data.getPhone();
        if(phone!="未登录")
            Get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.older_fragment_person, null);

        Login=v.findViewById(R.id.person_title_head_name);
        EditName=v.findViewById(R.id.person_title_head_namechange);
        NewOlder = v.findViewById(R.id.person_body_head_1);
        Setting = v.findViewById(R.id.person_body_head_3);
        More = v.findViewById(R.id.person_body_head_4);
        Help = v.findViewById(R.id.person_body_foot_5);
        Know = v.findViewById(R.id.person_body_foot_3);
        olderlist = v.findViewById(R.id.person_content_older_list);

        Login.setText(Data.getUsername());

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLoginStatus()==0){
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(),getUsername()+"已登录",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });

        EditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserManageActivity.class);
                startActivity(intent);
            }
        });

        Know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DailyKnowActivity.class);
                startActivity(intent);
            }
        });

        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MoreToolsActivity.class);
                startActivity(intent);
            }
        });

        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),HelpAllActivity.class);
                startActivity(intent);
            }
        });

        NewOlder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.getLoginStatus()==1) {
                    Intent intent = new Intent(getActivity(), AddOlderActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(NewOlder.getContext(), "请先登录", Toast.LENGTH_LONG).show();
            }
        });
        String phone = Data.getPhone();
        if(phone!="未登录")
            Get();
        return v;
    }


    // 获取数据库信息
    public void Get(){
        String url = Data.getUrl();
        String phone = Data.getPhone();
        final List<String> id = new ArrayList<>();
        final List<String> name = new ArrayList<>();
        final List<String> birthday = new ArrayList<>();
        final List<String> sex = new ArrayList<>();
        final List<String> emephone = new ArrayList<>();
        Log.d("Get","click");
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url+"/olds/get?phone="+phone).build();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == COMPLETED) {
                    OlderAdapter olderadpapter;
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
                    olderlist.setLayoutManager(layoutManager);
                    olderadpapter = new OlderAdapter(id,name,birthday,sex,emephone);
                    olderlist.setAdapter(olderadpapter);
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
                JSONArray jsonArray = jsonObject.getJSONArray("olds");
                if (jsonArray!=null){
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject0 = jsonArray.getJSONObject(i);
                        id.add(jsonObject0.getString("_id"));
                        name.add(jsonObject0.getString("name"));
                        birthday.add(jsonObject0.getString("birthday"));
                        sex.add(jsonObject0.getString("sex"));
                        emephone.add(jsonObject0.getString("phone"));
                    }
                    Message message = new Message();
                    message.what = COMPLETED;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
