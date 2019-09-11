package com.jack.carebaby.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BaseFragment;

import cn.bgbsk.babycare.global.Data;

import static cn.bgbsk.babycare.global.Data.getLoginStatus;
import static cn.bgbsk.babycare.global.Data.getUsername;

public class PersonFragmentOlder extends BaseFragment{


    private TextView Login;
    private ImageView EditName;
    private LinearLayout Setting;
    private LinearLayout More;
    private LinearLayout Help;
    private LinearLayout Know;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.older_fragment_person, null);

        Login=v.findViewById(R.id.person_title_head_name);
        EditName=v.findViewById(R.id.person_title_head_namechange);
        Setting = v.findViewById(R.id.person_body_head_3);
        More = v.findViewById(R.id.person_body_head_4);
        Help = v.findViewById(R.id.person_body_foot_5);
        Know = v.findViewById(R.id.person_body_foot_3);

        Login.setText(Data.getUsername());

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLoginStatus()==0){
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(),getUsername()+"已登陆",Toast.LENGTH_SHORT).show();
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

        return v;
    }


}
