package com.example.natarajan.transitproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ViewMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView mWebView=new WebView(ViewMap.this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.getSettings().setPluginsEnabled(true);
        mWebView.loadUrl("https://drive.google.com/open?id=0B1Jt01SaWo2mQ2tQM0V1WjBFUEE");
        setContentView(mWebView);
    }
}
