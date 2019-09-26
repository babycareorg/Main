package com.jack.carebaby.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BaseFragment;

import java.util.Calendar;

public class ToolsFragmentOlder extends BaseFragment{


    private Button Older_Tools_1;
    private Button Older_Tools_2;
    private Button Older_Tools_3;
    private Button Older_Tools_4;
    private Button Older_Tools_5;
    private Button Older_Tools_6;
    private Button Older_Tools_7;
    private Button Older_Tools_8;
    private Button Older_Tools_9;
    private Button Older_Tools_10;

    private TextView older_time;
    private TextView older_date;
    private TextView older_week;
    private TextView older_nongli;




    //日期获取
    Calendar calendar = Calendar.getInstance();




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.older_fragment_tools, null);




        Older_Tools_1=v.findViewById(R.id.older_tools_1);
        Older_Tools_2=v.findViewById(R.id.older_tools_2);
        Older_Tools_3=v.findViewById(R.id.older_tools_3);
        Older_Tools_4=v.findViewById(R.id.older_tools_4);
        Older_Tools_5=v.findViewById(R.id.older_tools_5);
        Older_Tools_6=v.findViewById(R.id.older_tools_6);
        Older_Tools_7=v.findViewById(R.id.older_tools_7);
        Older_Tools_8=v.findViewById(R.id.older_tools_8);
        Older_Tools_9=v.findViewById(R.id.older_tools_9);
        Older_Tools_10=v.findViewById(R.id.older_tools_10);
        older_time=v.findViewById(R.id.older_time);
        older_date=v.findViewById(R.id.older_date);
        older_week=v.findViewById(R.id.older_week);
        older_nongli=v.findViewById(R.id.older_nongli);


        new TimeThread().start(); //启动新的线程展示时间

        older_nongli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),LunarCalendarActivity.class);
                startActivity(intent);
            }
        });

        Older_Tools_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),OlderFMActivity.class);
                startActivity(intent);
            }
        });

        Older_Tools_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),OlderNewsActivity.class);
                startActivity(intent);
            }
        });


        Older_Tools_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),OlderCCTVActivity.class);
                startActivity(intent);
            }
        });

        Older_Tools_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),OlderHealthActivity.class);
                startActivity(intent);
            }
        });





        Older_Tools_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),OlderEatActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    //在主线程里面处理消息并更新UI界面
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    long sysTime = System.currentTimeMillis();//获取系统时间
                    CharSequence sysTimeStr = DateFormat.format("hh:mm:ss", sysTime);//时间显示格式
                    CharSequence sysDateStr = DateFormat.format("MM月dd日", sysTime);//时间显示格式

                    int week=calendar.get(Calendar.WEDNESDAY);

                    older_time.setText(sysTimeStr);
                    //older_date.setText(month+"月"+day+"日");
                    older_date.setText(sysDateStr);
                    older_week.setText("星期"+week);
                    break;
                default:
                    break;

            }
        }
    };


}
