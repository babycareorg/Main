package com.jack.carebaby.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BaseFragment;
import com.jack.carebaby.utils.WebViewFragmentUtil;

public class SystemHospitalFragment extends BaseFragment {


    private LinearLayout Handle;
    private LinearLayout Yimiao;
    private LinearLayout Ask;
    private LinearLayout Note;

    private WebView hospital_webview;
    private String url;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_system_hospital, null);
        
        hospital_webview=v.findViewById(R.id.hospital_webview);

        Handle=v.findViewById(R.id.hospital_title_body_1);
        Yimiao=v.findViewById(R.id.hospital_title_body_2);
        Ask=v.findViewById(R.id.hospital_title_body_3);
        Note=v.findViewById(R.id.hospital_title_body_4);



       Handle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HospitalHandleActivity.class) ;
                startActivity(intent);

            }
        });

        Ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HospitalAskActivity.class) ;
                startActivity(intent);

            }
        });


        url="https://m.drmed.cn/self-diagnosis";
        hospital_webview.loadUrl(url);

        WebViewFragmentUtil webViewFragmentUtil=new WebViewFragmentUtil();

        webViewFragmentUtil.WebViewUtil(hospital_webview);

        return v;
    }
}
