package com.mobilyte.chartadvice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobilyte.chartadvice.Api.Apis;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.ui.classes.BaseActivity;

public class DefaultUserDescription extends BaseActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView serviceImage;
    private TextView serviceDescription;
    private TextView getServiceDescriptiontwo;
    private TextView readMore;
    private String title = "";
    private ImageView backbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_user_description);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolBarTitle);
        serviceImage = (ImageView) findViewById(R.id.serviceImage);
        serviceDescription = (TextView) findViewById(R.id.serviceDescription);
        getServiceDescriptiontwo=(TextView)findViewById(R.id.serviceDescriptiontwo);
        readMore = (TextView) findViewById(R.id.readMore);
        backbutton=(ImageView)findViewById(R.id.back_button);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userDefault = new Intent(DefaultUserDescription.this, DefaultUser.class);
                startActivity(userDefault);
                finish();
            }
        });


        if (getIntent().getExtras() != null && !TextUtils.isEmpty(getIntent().getExtras().getString("title"))) {
            title = getIntent().getExtras().getString("title");
            setView();
        }
    }

    private void setView() {
        if (title.equalsIgnoreCase(getString(R.string.niftyEdgeTitle))) {
            setViewData(getString(R.string.niftyEdgeTitle), getString(R.string.niftyEdgeContent), R.drawable.nify_edge_default, Apis.nifty_edge_url);
        } else if (title.equalsIgnoreCase(getString(R.string.futureGainTitle))) {
            setViewData(getString(R.string.futureGainTitle), getString(R.string.futureGainContent), R.drawable.stock_gain_default, Apis.future_gain_url);
        } else if (title.equalsIgnoreCase(getString(R.string.optionGainTitle))) {
            setViewData(getString(R.string.optionGainTitle), getString(R.string.optionGainContent), R.drawable.option_gain_default, Apis.option_gain_url);
        } else if (title.equalsIgnoreCase(getString(R.string.equityGainTitle))) {
            setViewData(getString(R.string.equityGainTitle), getString(R.string.equityGainContent), R.drawable.equity_gain_default, Apis.equity_gain_url);
        } else if (title.equalsIgnoreCase(getString(R.string.traderCenterTitle))) {
            setViewData(getString(R.string.traderCenterTitle), getString(R.string.traderCenterContent), R.drawable.trader_central_default, Apis.trader_center_url);
        }
    }

    private void setViewData(final String title, String content, int imageResource, final String url) {
        toolbarTitle.setText(title);
        serviceImage.setImageResource(imageResource);
        serviceDescription.setText(content);
        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DefaultUserDescription.this, Default_WebView.class);
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent userDefault = new Intent(this, DefaultUser.class);
        startActivity(userDefault);
        finish();
    }
}
