package com.jack.carebaby.ui;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BasePage;

public class DailyTripActivity extends BasePage {

    private WebView trip_webview;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_trip);

        trip_webview=findViewById(R.id.trip_webview);

        url="https://m.ctrip.com/html5/?sourceid=497&allianceid=4897&sid=182042&sepopup=150";
        trip_webview.loadUrl(url);

        //支持app内js交互
        trip_webview.getSettings().setJavaScriptEnabled(true);

        //自适应屏幕
        trip_webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        trip_webview.getSettings().setLoadWithOverviewMode(true);

        //设置了支持缩放
        trip_webview.getSettings().setSupportZoom(true);

        //扩大比例缩放
        trip_webview.getSettings().setUseWideViewPort(true);

        trip_webview.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }


            //在开始加载网页时会回调
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                Toast.makeText(DailyTripActivity.this, "正在加载...", Toast.LENGTH_LONG).show();

            }
            //加载错误的时候会回调
            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Toast.makeText(DailyTripActivity.this, "加载失败", Toast.LENGTH_LONG).show();

                    return;
                }

            }

            //加载错误的时候会回调
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                if (webResourceRequest.isForMainFrame()) {
                    Toast.makeText(DailyTripActivity.this, "加载失败", Toast.LENGTH_LONG).show();

                }
            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.trip_menu,menu);

        return true;
    }

    //监听菜单点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.trip1:
                url="https://m.tuniu.com/?p=14820&cmpid=mkt_03033903&utm_source=m.baidu.com&utm_campaign=baidupz&utm_medium=baidupz";
                trip_webview.loadUrl(url);
                Toast.makeText(DailyTripActivity.this, "加载途牛...", Toast.LENGTH_LONG).show();


                break;

            case R.id.trip2:

                url="https://m.mafengwo.cn/";
                trip_webview.loadUrl(url);
                Toast.makeText(DailyTripActivity.this, "加载马蜂窝...", Toast.LENGTH_LONG).show();

                break;

            case R.id.trip3:
                url="https://m.qyer.com/";
                trip_webview.loadUrl(url);
                Toast.makeText(DailyTripActivity.this, "加载穷游...", Toast.LENGTH_LONG).show();

                break;

            case R.id.trip4:
                url="https://m.ly.com/";
                trip_webview.loadUrl(url);
                Toast.makeText(DailyTripActivity.this, "加载同城...", Toast.LENGTH_LONG).show();

                break;
            case R.id.trip5:
                url="https://m.lvmama.com/?losc=019926&utm_source=baidu&utm_medium=zhuanqu&utm_campaign=wap";
                trip_webview.loadUrl(url);
                Toast.makeText(DailyTripActivity.this, "加载驴妈妈...", Toast.LENGTH_LONG).show();


                break;
            case R.id.trip6:
                DailyTripActivity.this.finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //html返回键重置
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && trip_webview.canGoBack()) {
            trip_webview.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
