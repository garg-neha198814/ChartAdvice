package com.mobilyte.chartadvice.Views.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.mobilyte.chartadvice.R;

public class Futuregain extends AppCompatActivity {

    //setting variable
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futuregain);
        //intializing variable
        webview = (WebView) findViewById(R.id.webviewfuturegains);
        //setting intent for web view
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://www.chartadvise.com/");

    }
}
