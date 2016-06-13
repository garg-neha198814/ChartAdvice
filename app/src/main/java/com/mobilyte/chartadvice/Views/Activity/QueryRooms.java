package com.mobilyte.chartadvice.Views.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.mobilyte.chartadvice.R;

public class QueryRooms extends AppCompatActivity {
    //setting variable
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_rooms);
        //intializing variable
        webview = (WebView) findViewById(R.id.webviewfuturegains);
        //setting intent for webview
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://www.chartadvise.com/");

    }
}
