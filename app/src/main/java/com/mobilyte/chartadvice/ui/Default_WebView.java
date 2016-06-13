package com.mobilyte.chartadvice.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.ui.classes.BaseActivity;

public class Default_WebView extends BaseActivity {

    //setting variable
    private WebView webView;
    private android.support.v7.widget.Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView mbackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_webview);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolBarTitle);
        mbackbutton = (ImageView)toolbar.findViewById(R.id.back_button);
        mbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toolbarTitle.getText().toString().equalsIgnoreCase(getString(R.string.aboutUsTitle))) {
                    Intent service = new Intent(Default_WebView.this, DefaultUser.class);
                    startActivity(service);
                    finish();
                } else {
                    Intent userDefault = new Intent(Default_WebView.this, DefaultUser.class);
                    startActivity(userDefault);
                    finish();
                }
            }
        });

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebClient());

        if (getIntent().getExtras() != null) {
            if (!TextUtils.isEmpty(getIntent().getExtras().getString("url"))) {
                webView.loadUrl(getIntent().getExtras().getString("url"));
            }
            if (!TextUtils.isEmpty(getIntent().getExtras().getString("title"))) {
                toolbarTitle.setText(getIntent().getExtras().getString("title"));
            }
        } else {
            webView.loadUrl("http://www.chartadvise.com/advisory/");
        }
    }

    class WebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            showDialog();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            hideDialog();
        }
    }

    @Override
    public void onBackPressed() {
        if (toolbarTitle.getText().toString().equalsIgnoreCase(getString(R.string.aboutUsTitle))) {
            Intent service = new Intent(this, ServicesActivity.class);
            startActivity(service);
            finish();
        } else {
            Intent userDefault = new Intent(this, DefaultUser.class);
            startActivity(userDefault);
            finish();
        }
    }
}
