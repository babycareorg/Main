package com.jack.carebaby.ui;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BaseFragment;

public class CameraFragmentOlder extends BaseFragment{


    private WebView camera_webview;
    private ImageView ivError;
    private EditText setUrl;
    private Button setUrl_Button;
    private TextView camera_state;

    String url;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_camera, null);

        camera_webview=v.findViewById(R.id.camera_webview);
        ivError=v.findViewById(R.id.camera_webview_error);
        setUrl=v.findViewById(R.id.camera_seturl);
        setUrl_Button=v.findViewById(R.id.camera_seturl_button);
        camera_state=v.findViewById(R.id.camera_state);


        camera_webview.loadUrl("http://192.168.137.174");

        setUrl_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url="http://"+setUrl.getText();
                camera_webview.loadUrl(url);
                //refresh();
            }
        });


        camera_webview.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }


            //在开始加载网页时会回调
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                ivError.setVisibility(View.INVISIBLE);
                camera_webview.setVisibility(View.VISIBLE);
                camera_state.setText("开");
            }
            //加载错误的时候会回调
            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return;
                }
                ivError.setVisibility(View.VISIBLE);
                camera_webview.setVisibility(View.INVISIBLE);
                camera_state.setText("关");
            }

            //加载错误的时候会回调
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                if (webResourceRequest.isForMainFrame()) {
                    ivError.setVisibility(View.VISIBLE);
                    camera_webview.setVisibility(View.INVISIBLE);
                    camera_state.setText("关");
                }
            }


        });

        //支持app内js交互
        camera_webview.getSettings().setJavaScriptEnabled(true);

        //自适应屏幕
        camera_webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        camera_webview.getSettings().setLoadWithOverviewMode(true);

        //设置了支持缩放
        camera_webview.getSettings().setSupportZoom(true);

        //扩大比例缩放
        camera_webview.getSettings().setUseWideViewPort(true);

        //设置是否出现缩放工具
        camera_webview.getSettings().setBuiltInZoomControls(true);


        return v;
    }


}
