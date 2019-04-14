package com.smartjobs.smartjobs.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.smartjobs.smartjobs.R;

public class WebviewActivity extends AppCompatActivity {
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        web.loadUrl("https://cashmobile.online/main/pay?to=banj&amount=50&description=Pour Offre de service");
    }
}
