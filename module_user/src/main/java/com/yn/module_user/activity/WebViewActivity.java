package com.yn.module_user.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yn.module_user.R;

import me.goldze.mvvmhabit.utils.RouteUtils;

@Route(path = RouteUtils.Me_WebView)
public class WebViewActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
        mWebview.loadUrl(getIntent().getStringExtra("url"));
    }
}
