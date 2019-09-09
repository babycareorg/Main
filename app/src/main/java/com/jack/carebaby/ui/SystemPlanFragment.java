package com.jack.carebaby.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jack.carebaby.R;
import com.jack.carebaby.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.bgbsk.babycare.global.Data;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SystemPlanFragment extends BaseFragment {

    private SharedPreferences loginSP;
    private SharedPreferences.Editor loginEdit;
    private String url = Data.getUrl();

    private TextView system_title_head_note;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_system_plan, null);


        system_title_head_note=v.findViewById(R.id.system_title_head_note);



        system_title_head_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPlanDialog();

            }
        });




        loginSP = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        if (loginSP.getString("phone",null)!= null){
            String phone = loginSP.getString("phone",null);
            String password = loginSP.getString("password",null);

            Map map = new HashMap();
            map.put("phone", phone);
            map.put("password", password);
            String param = JSON.toJSONString(map);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            final RequestBody requestBody = RequestBody.create(param, mediaType);
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder().post(requestBody).url(url + "/user/login").build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d("LoginError", e.getMessage());
                }

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    JSONObject jsonObject = JSON.parseObject(response.body().string());
                    int status = jsonObject.getInteger("status");

                    if (status == 200) {
                        Data.setPhone(jsonObject.getString("phone"));
                        Data.setUsername(jsonObject.getString("username"));
                        Data.setCreated(jsonObject.getTimestamp("created"));
                        Data.setLoginStatus(1);
                        //这里添加登录成功相关东西
                    }

                }
            });

        }

        return v;
    }

   private void showPlanDialog() {

        final AlertDialog mAlertDialog = new AlertDialog.Builder(getContext()).show();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.help_fragment_plan,null);
        mAlertDialog.setContentView(view);

        mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

                mAlertDialog.cancel();
            }
        });
        Window window = mAlertDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0x00000000));
    }
}
