package com.mobilyte.chartadvice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobilyte.chartadvice.Adapter.ArchivesResult_Adapter;
import com.mobilyte.chartadvice.Api.ApiHandler;
import com.mobilyte.chartadvice.Api.ApiResponse;
import com.mobilyte.chartadvice.Api.Apis;
import com.mobilyte.chartadvice.Modal.ArchivesFilter_Data;
import com.mobilyte.chartadvice.Modal.Archives_FilterBean;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.interfaces.RetryInterface;
import com.mobilyte.chartadvice.ui.classes.BaseActivity;
import com.mobilyte.chartadvice.utility.Preferences;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Archives_Result extends BaseActivity implements ApiResponse, RetryInterface {

    //necessary variables
    private String stockdata, viewdata, analystdata, s_date, f_date;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArchivesResult_Adapter adapter;
    private ImageView back;
    private RelativeLayout relative;
    private Toolbar mToolbar;
    public TextView marchivemessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archives__result);


            mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            relative = (RelativeLayout) findViewById(R.id.parentPanel);
            back = (ImageView) findViewById(R.id.back_press);
            marchivemessage = (TextView) findViewById(R.id.archivemessage);
            marchivemessage.setVisibility(View.VISIBLE);
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            //intializing Recyler view
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            //getting all String from fragment

        if(getIntent().getExtras().getString("status").equals("1")){
            marchivemessage.setVisibility(View.VISIBLE);
        }else {
            Intent intent = getIntent();
            stockdata = intent.getStringExtra("StockData");
            viewdata = intent.getStringExtra("ViewData");
            analystdata = intent.getStringExtra("AnalystData");
            f_date = intent.getStringExtra("FirstDate");
            s_date = intent.getStringExtra("SecondDate");
            loadData();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.archives_tool, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.Archivefilter:
                Intent archivesActivity = new Intent(Archives_Result.this, Archive_Filter.class);
                startActivity(archivesActivity);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void loadData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("user_id", Preferences.getInstance(this).getUserId());
        hashMap.put("product_name", getString(R.string.Traders_center));
        hashMap.put("token", getString(R.string.token_String));
        hashMap.put("view_name", viewdata);
        hashMap.put("from_date", f_date);
        hashMap.put("to_date", s_date);
        hashMap.put("stock_name", stockdata);
        hashMap.put("analyst_name", analystdata);
        showDialog();
        ApiHandler.getInstance(Archives_Result.this).GetData(Apis.trader_center_archives, this, 1, hashMap, Apis.trader_center_archives_tag);
    }

    //onsucess methord of api
    @Override
    public void onSuccess(String response, String tag) {
        try {
            hideDialog();
            if (tag.equalsIgnoreCase(Apis.trader_center_archives_tag)) {
                JSONObject object = new JSONObject(response);
                if (object.optInt("status") == 1 && TextUtils.isEmpty(object.optString("data"))) {
                    marchivemessage.setVisibility(View.VISIBLE);
                } else if (object.optInt("status") == 1) {
                    marchivemessage.setVisibility(View.GONE);
                    Gson gson = new Gson();
                    Archives_FilterBean dataBean = gson.fromJson(response, Archives_FilterBean.class);
                    adapter = new ArchivesResult_Adapter((ArrayList<ArchivesFilter_Data>) dataBean.getData(), this);
                    mRecyclerView.setAdapter(adapter);
                } else {

                    marchivemessage.setVisibility(View.VISIBLE);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //onFailure methord of api
    @Override
    public void onFailure(Throwable t, String tag) {
        hideDialog();

        if (tag.equalsIgnoreCase(Apis.trader_center_archives_tag)) {
            marchivemessage.setVisibility(View.GONE);
          snackBarRetry(relative, tag, this);
        }
    }

    //setting onback press
    @Override
    public void onBackPressed() {
        Intent filter = new Intent(Archives_Result.this, TradersCentral.class);
        startActivity(filter);
        finish();
    }


    @Override
    public void onRetry(String tag) {
        switch (tag) {
            case Apis.trader_center_archives_tag:
                loadData();
                break;
        }
    }
}
