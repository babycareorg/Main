package com.jack.carebaby.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BaseFragment;

public class SystemDailyFragment extends BaseFragment {

    private LinearLayout trip;
    private LinearLayout eat;
    private LinearLayout music;
    private LinearLayout know;

    private WebView community_webview;
    private String url;

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


        community_webview=v.findViewById(R.id.community_webview);

        url="http://m.mama.cn/";
        community_webview.loadUrl(url);

        //支持app内js交互
        community_webview.getSettings().setJavaScriptEnabled(true);

        //自适应屏幕
        community_webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        community_webview.getSettings().setLoadWithOverviewMode(true);

        //设置了支持缩放
        community_webview.getSettings().setSupportZoom(true);

        //扩大比例缩放
        community_webview.getSettings().setUseWideViewPort(true);

        community_webview.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }


            //在开始加载网页时会回调
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                //Toast.makeText(getActivity(), "正在加载...", Toast.LENGTH_LONG).show();
                community_webview.setVisibility(View.VISIBLE);

            }
            //加载错误的时候会回调
            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    return;
                }
                Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_LONG).show();
                community_webview.setVisibility(View.INVISIBLE);

            }

            //加载错误的时候会回调
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                if (webResourceRequest.isForMainFrame()) {


                }

                Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_LONG).show();
                community_webview.setVisibility(View.INVISIBLE);
            }


        });
        


        return v;
    }



}
