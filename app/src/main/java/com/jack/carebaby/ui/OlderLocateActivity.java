package com.jack.carebaby.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jack.carebaby.R;

public class OlderLocateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_older_locate);

        Button btn_protect = findViewById(R.id.protect);
        //导航功能
        btn_protect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//               Intent intent = new Intent(Maintabs_BActivity.this, Maintabs_AActivity.class);
//               startActivity(intent);

                Intent intent = getPackageManager().getLaunchIntentForPackage("com.autonavi.minimap");
// 这里如果intent为空，就说名没有安装要跳转的应用嘛
                if (intent != null) {
                    // 这里跟Activity传递参数一样的嘛，不要担心怎么传递参数，还有接收参数也是跟Activity和Activity传参数一样
//                    intent.putExtra("name", "Liu xiang");
//                    intent.putExtra("birthday", "1983-7-13");
                    startActivity(intent);
                } else {
                    // 没有安装要跳转的app应用，提醒一下
                    Toast.makeText(getApplicationContext(), "哟，赶紧下载安装高德地图吧~", Toast.LENGTH_LONG).show();
                }

                ////shouhu
//                aMapLocation.getLocationType();
//                double a = aMapLocation.getLatitude();//获取纬度
//                Log.i("纬度", String.valueOf(a));
//                double b =  aMapLocation.getLongitude();//获取经度
//                String  friend="2508074836@qq.com";
//                sendLocation(String.valueOf(a),String.valueOf(b),friend);


            }
        });


    }
}
