package com.jack.carebaby.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BasePage;

import static cn.bgbsk.babycare.global.Data.getPhone;
import static cn.bgbsk.babycare.global.Data.getUsername;

public class UserManageActivity extends BasePage {


    private TextView name;
    private TextView title;
    private TextView phone;

    private LinearLayout ChangeUser;
    private LinearLayout ChangePassword;
    private LinearLayout Exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage);

        initView();

        name.setText(getUsername());
        title.setText(getUsername());
        phone.setText(getPhone());

        ChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserManageActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent(UserManageActivity.this,LoginActivity.class);
                startActivity(intent);*/
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent(UserManageActivity.this,LoginActivity.class);
                startActivity(intent);*/
            }
        });

    }


    private void initView(){
        name=findViewById(R.id.user_manage_header_topname);
        title=findViewById(R.id.user_manage_header_title);
        phone=findViewById(R.id.user_manage_header_phone);
        ChangeUser=findViewById(R.id.user_manage_login);
        ChangePassword=findViewById(R.id.user_manage_password);
        Exit=findViewById(R.id.user_manage_exit);
    }
}
