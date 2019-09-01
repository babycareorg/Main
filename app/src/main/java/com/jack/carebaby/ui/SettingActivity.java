package com.jack.carebaby.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BasePage;
import com.jack.carebaby.service.FloatWindowServer;
import com.suke.widget.SwitchButton;


public class SettingActivity extends BasePage {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        if(NavUtils.getParentActivityName(SettingActivity.this)!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        //设置点击监听
        /**个人信息*/
        findViewById(R.id.setting_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent(SettingActivity.this,#.class);
                startActivity(intent);*/
            }
        });

        /**宝贝信息*/

        findViewById(R.id.setting_baby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(SettingActivity.this, "尽请期待", Toast.LENGTH_SHORT).show();
            }
        });

        /**消息通知*/

        findViewById(R.id.setting_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "尽请期待", Toast.LENGTH_SHORT).show();
            }
        });

        /**桌面悬浮按钮*/
        com.suke.widget.SwitchButton switchButton = findViewById(R.id.setting_floatwindow);

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                if(isChecked==true){
                    Intent intent=new Intent(SettingActivity.this,FloatWindowServer.class);
                    startService(intent);
                }
                else{
                    Intent intent=new Intent(SettingActivity.this,FloatWindowServer.class);
                    stopService(intent);
                }
            }
        });


        /**权限管理*/
        findViewById(R.id.setting_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //系统权限管理页面
                startActivity(getAppDetailSettingIntent());

            }
        });

        /**辅助功能*/
        findViewById(R.id.setting_other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "尽请期待", Toast.LENGTH_SHORT).show();
            }
        });

        /**团队介绍*/
        findViewById(R.id.setting_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(SettingActivity.this, "团队介绍", Toast.LENGTH_SHORT).show();
                showGrounpDialog();
            }
        });

    }



    /*权限界面*/

    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }


    /*babyboxs团队简介页面*/
    private void showGrounpDialog() {

        final AlertDialog mAlertDialog = new AlertDialog.Builder(this).show();
        View view = LayoutInflater.from(this).inflate(R.layout.fragment_setting_group,null);
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
