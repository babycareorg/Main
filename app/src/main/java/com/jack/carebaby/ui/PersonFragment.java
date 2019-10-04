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
import com.memorandum.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bgbsk.babycare.global.Data;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static cn.bgbsk.babycare.global.Data.getCountingshow;
import static cn.bgbsk.babycare.global.Data.getLoginStatus;
import static cn.bgbsk.babycare.global.Data.getUsername;
import static cn.bgbsk.babycare.global.Data.setCountingshow;

public class PersonFragment extends BaseFragment {

    private TextView Login;
    private TextView Count;
    private ImageView EditName;

    private LinearLayout NewBaby;
    private LinearLayout Setting;
    private LinearLayout More;
    private LinearLayout Help;
    private LinearLayout Know;
    private LinearLayout Test;
    private LinearLayout Record;
    private LinearLayout Info;

    private static final int COMPLETED = 0;
    private RecyclerView babylist;

    private int count_baby;
    private String register_time;

    private String count_show;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Test = v.findViewById(R.id.person_body_foot_4);
        Record = v.findViewById(R.id.person_body_foot_2);
        Info = v.findViewById(R.id.person_body_foot_1);
        babylist = v.findViewById(R.id.person_content_baby_list);
        Count = v.findViewById(R.id.person_title_head_time);

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

        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),BabyTestActivity.class);
                startActivity(intent);
            }
        });

        Record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), BabyInfoShowActivity.class);
                startActivity(intent);
            }
        });

        Get();
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

                //设置宝宝数展示
                count_baby=jsonArray.size();
                register_time=String.valueOf(Data.getCreated());


                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());//获取当前时间

                try
                {
                    Date d1 = df.parse(register_time);
                    Date d2 = df.parse(String.valueOf(df.format(date)));
                    long diff = d2.getTime()-d1.getTime();//这样得到的差值是微秒级别
                    long days = diff / (1000 * 60 * 60 * 24);
                    /*long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                    long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);*/

                    count_show="您已入驻"+days+"天，拥有宝贝"+count_baby+"个";
                    setCountingshow(count_show);
                }
                catch (Exception e)
                {
                }




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


        Count.setText(String.valueOf(getCountingshow()));
    }


}
