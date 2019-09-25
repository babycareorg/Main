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
import com.jack.carebaby.utils.BabyAdapter;

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

public class PersonFragment extends BaseFragment {

    private TextView Login;
    private ImageView EditName;

    private LinearLayout NewBaby;
    private LinearLayout Setting;
    private LinearLayout More;
    private LinearLayout Help;
    private LinearLayout Know;

    private static final int COMPLETED = 0;
    private RecyclerView babylist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Get();
    }

    public void onResume(){
        super.onResume();
        Get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_person, null);
        Login=v.findViewById(R.id.person_title_head_name);
        EditName=v.findViewById(R.id.person_title_head_namechange);
        NewBaby = v.findViewById(R.id.person_body_head_1);
        Setting = v.findViewById(R.id.person_body_head_3);
        More = v.findViewById(R.id.person_body_head_4);
        Help = v.findViewById(R.id.person_body_foot_5);
        Know = v.findViewById(R.id.person_body_foot_3);
        babylist = v.findViewById(R.id.person_content_baby_list);

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

        NewBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Data.getLoginStatus()==1) {
                    Intent intent = new Intent(getActivity(), AddBabyActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(NewBaby.getContext(), "请先登录", Toast.LENGTH_LONG).show();
            }
        });

        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingActivity.class);
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

        EditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserManageActivity.class);
                startActivity(intent);
            }
        });

        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ApplicationGetActivity.class);
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
        return v;
    }

    /*@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser){
            Login.setText(Data.getUsername());
        }else {

        }
    }*/

    // 获取数据库信息
    public void Get(){
        String url = Data.getUrl();
        String phone = Data.getPhone();
        final List<String> id = new ArrayList<>();
        final List<String> name = new ArrayList<>();
        final List<String> birthday = new ArrayList<>();
        final List<String> sex = new ArrayList<>();
        final JSONArray jsonArray = new JSONArray();
        Log.d("Get","click");
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url+"/baby/get?phone="+phone).build();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == COMPLETED) {
                    BabyAdapter babyadpapter;
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
                    babylist.setLayoutManager(layoutManager);
                    babyadpapter = new BabyAdapter(id,name,birthday,sex);
                    babylist.setAdapter(babyadpapter);
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
                JSONArray jsonArray = jsonObject.getJSONArray("babys");
                if (jsonArray!=null){
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject0 = jsonArray.getJSONObject(i);
                        id.add(jsonObject0.getString("_id"));
                        name.add(jsonObject0.getString("name"));
                        birthday.add(jsonObject0.getString("birthday"));
                        sex.add(jsonObject0.getString("sex"));
                    }
                    Message message = new Message();
                    message.what = COMPLETED;
                    handler.sendMessage(message);
                }
            }
        });
    }


}
