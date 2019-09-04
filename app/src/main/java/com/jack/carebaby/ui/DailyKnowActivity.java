package com.jack.carebaby.ui;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
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

public class DailyKnowActivity extends AppCompatActivity {

    private WebView know_webview;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_know);

        know_webview=findViewById(R.id.know_webview);

        url="http://www.jiankang123.net/";
        know_webview.loadUrl(url);

        //支持app内js交互
        know_webview.getSettings().setJavaScriptEnabled(true);

        //自适应屏幕
        know_webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        know_webview.getSettings().setLoadWithOverviewMode(true);

        //设置了支持缩放
        know_webview.getSettings().setSupportZoom(true);

        //扩大比例缩放
        know_webview.getSettings().setUseWideViewPort(true);

        know_webview.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }


            //在开始加载网页时会回调
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                Toast.makeText(DailyKnowActivity.this, "正在加载...", Toast.LENGTH_LONG).show();

            }
            //加载错误的时候会回调
            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Toast.makeText(DailyKnowActivity.this, "加载失败", Toast.LENGTH_LONG).show();

                    return;
                }

            }

            //加载错误的时候会回调
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                if (webResourceRequest.isForMainFrame()) {
                    Toast.makeText(DailyKnowActivity.this, "加载失败", Toast.LENGTH_LONG).show();

                }
            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.know_menu,menu);

        return true;
    }

    //监听菜单点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.know1:
                url="http://dtxinshenghuo.com/";
                know_webview.loadUrl(url);
                Toast.makeText(this, "加载生活百科...", Toast.LENGTH_LONG).show();


                break;

            case R.id.know2:

                url="http://www.8585.online/";
                know_webview.loadUrl(url);
                Toast.makeText(this, "加载生活技巧...", Toast.LENGTH_LONG).show();

                break;

            case R.id.know3:
                url="http://www.bkzx.cn/";
                know_webview.loadUrl(url);
                Toast.makeText(this, "加载百科在线...", Toast.LENGTH_LONG).show();

                break;

            case R.id.know4:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    //html返回键重置
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && know_webview.canGoBack()) {
            know_webview.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
