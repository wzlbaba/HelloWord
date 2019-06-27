package com.wang.day_01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
        mWeb.loadUrl("https://www.baidu.com/");
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.getSettings().setJavaScriptEnabled(true);
    }
}
