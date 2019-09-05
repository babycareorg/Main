package com.jack.carebaby.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.jack.carebaby.utils.WebViewFragmentUtil;

import com.jack.carebaby.R;
import com.jack.carebaby.base.BaseFragment;

public class SystemLiveFragment extends BaseFragment {

    private WebView live_webview;
    private String url;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_system_live, null);

        live_webview=v.findViewById(R.id.live_webview);


        url="https://www.evolife.cn/category/homeware/page/11";
        live_webview.loadUrl(url);

        WebViewFragmentUtil webViewFragmentUtil=new WebViewFragmentUtil();

        webViewFragmentUtil.WebViewUtil(live_webview);


        return v;
    }
}
