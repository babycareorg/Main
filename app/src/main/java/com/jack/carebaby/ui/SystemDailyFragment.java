package com.jack.carebaby.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BaseFragment;

public class SystemDailyFragment extends BaseFragment {

    private LinearLayout trip;
    private LinearLayout eat;
    private LinearLayout music;
    private LinearLayout know;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_system_daily, null);

        trip=v.findViewById(R.id.daily_title_body_4);
        eat=v.findViewById(R.id.daily_title_body_1);
        know=v.findViewById(R.id.daily_title_body_2);
        music=v.findViewById(R.id.daily_title_body_3);

        trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DailyTripActivity.class) ;
                startActivity(intent);

            }
        });

        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DailyEatActivity.class) ;
                startActivity(intent);

            }
        });

        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DailyKnowActivity.class) ;
                startActivity(intent);

            }
        });


        return v;
    }
}
