package com.jack.carebaby.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BaseFragment;

import cn.bgbsk.babycare.global.Data;

public class PersonFragment extends BaseFragment {

    private TextView Login;
    private ImageView EditName;
    private LinearLayout Setting;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_person, null);

        Login=v.findViewById(R.id.person_title_head_name);
        EditName=v.findViewById(R.id.person_title_head_namechange);
        Setting = v.findViewById(R.id.person_body_head_3);

        Login.setText(Data.getUsername());
//        Log.i("aaaaaa", Data.getPhone());

        EditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });

        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });



        return v;
    }




}
