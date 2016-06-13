package com.mobilyte.chartadvice.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.mobilyte.chartadvice.Adapter.FilterAdapter;
import com.mobilyte.chartadvice.Api.ApiHandler;
import com.mobilyte.chartadvice.Api.ApiResponse;
import com.mobilyte.chartadvice.Api.Apis;
import com.mobilyte.chartadvice.Modal.TradersCenterBean;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.interfaces.RetryInterface;
import com.mobilyte.chartadvice.ui.classes.BaseActivity;
import com.mobilyte.chartadvice.utility.Preferences;

import java.util.ArrayList;
import java.util.HashMap;

public class TradersCenter_Filter extends BaseActivity implements ApiResponse, RetryInterface {
    //necessary variables
    private Button mStock_btn, mAnalyst_btn, mView_bytn, mApply, mclear;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FilterAdapter adapter;
    private ArrayList<TradersCenterBean> list;
    Context context;
    private TradersCenterBean data;
    private ArrayList<String> filter;
    private Toolbar mToolbar;
    private ImageView back;
    private RelativeLayout relative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traders_center__filter);
        //intializing variable
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        back = (ImageView) mToolbar.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mStock_btn = (Button) findViewById(R.id.stock);
        mAnalyst_btn = (Button) findViewById(R.id.analyst);
        mView_bytn = (Button) findViewById(R.id.view);
        mApply = (Button) findViewById(R.id.Apply_enter);
        mclear = (Button) findViewById(R.id.back_enter);
        relative = (RelativeLayout) findViewById(R.id.parentview);
        mclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFilters();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });

        filter = new ArrayList<>();
        //intializing Recyler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_viewfilter);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        loadData();
        //getting  data from strings
        mApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent filter = new Intent(TradersCenter_Filter.this, TradersCentral.class);
                    if (getStockCheckedPosition() == -1) {
                        filter.putExtra("stockSelection", "");
                    } else {
                        filter.putExtra("stockSelection", data.getStockList().get(getStockCheckedPosition()));
                    }

                    if (getAnalystCheckedPosition() == -1) {
                        filter.putExtra("analystSelection", "");
                    } else {
                        filter.putExtra("analystSelection", data.getAnalystList().get(getAnalystCheckedPosition()));
                    }

                    if (getViewCheckedPosition() == -1) {
                        filter.putExtra("viewSelection", "");
                    } else {
                        filter.putExtra("viewSelection", data.getViewsList().get(getViewCheckedPosition()));
                    }
                    startActivity(filter);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadData() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("product_name", getString(R.string.Traders_center));
        hashMap.put("id", Preferences.getInstance(this).getUserId());
        hashMap.put("current_date", "2016-03-21");
        hashMap.put("token", getString(R.string.token_String));
        showDialog();
        ApiHandler.getInstance(TradersCenter_Filter.this).GetData(Apis.trader_center_filter_url, this, 1, hashMap, Apis.trader_center_filter_tag);
    }

    @Override
    public void onSuccess(String response, String tag) {
        hideDialog();
        if (tag.equalsIgnoreCase(Apis.trader_center_filter_tag)) {
            Gson gson = new Gson();
            data = gson.fromJson(response, TradersCenterBean.class);
            if (data.getStatus() == 1) {

                adapter = new FilterAdapter(TradersCenter_Filter.this, TradersCenter_Filter.this, data.getStockList(), 1);
                mRecyclerView.setAdapter(adapter);

                mStock_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter = new FilterAdapter(TradersCenter_Filter.this, TradersCenter_Filter.this, data.getStockList(), 1);
                        mRecyclerView.setAdapter(adapter);

                    }
                });
                mAnalyst_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        adapter = new FilterAdapter(TradersCenter_Filter.this, TradersCenter_Filter.this, data.getAnalystList(), 2);
                        mRecyclerView.setAdapter(adapter);
                    }
                });

                mView_bytn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        adapter = new FilterAdapter(TradersCenter_Filter.this, TradersCenter_Filter.this, data.getViewsList(), 3);
                        mRecyclerView.setAdapter(adapter);
                    }
                });

            } else {
                snackBarMessage(relative, response.toString());
            }
        }
    }

    //on failure of api
    @Override
    public void onFailure(Throwable t, String tag) {
        hideDialog();
        if (tag.equalsIgnoreCase(Apis.trader_center_filter_tag)) {
            snackBarRetry(relative, tag, this);
        }
    }

    //on back press activity
    @Override
    public void onBackPressed() {
        Intent filter = new Intent(TradersCenter_Filter.this, TradersCentral.class);
        startActivity(filter);
        finish();
    }

    @Override
    public void onRetry(String tag) {
        switch (tag) {
            case Apis.trader_center_filter_tag:
                loadData();
                break;
        }
    }

    // Filter positions set up
    public int getStockCheckedPosition() {
        return stockCheckedPosition;
    }

    public void setStockCheckedPosition(int stockCheckedPosition) {
        this.stockCheckedPosition = stockCheckedPosition;
    }

    public int getAnalystCheckedPosition() {
        return analystCheckedPosition;
    }

    public void setAnalystCheckedPosition(int analystCheckedPosition) {
        this.analystCheckedPosition = analystCheckedPosition;
    }

    public int getViewCheckedPosition() {
        return viewCheckedPosition;
    }

    public void setViewCheckedPosition(int viewCheckedPosition) {
        this.viewCheckedPosition = viewCheckedPosition;
    }

    public void clearFilters() {
        setStockCheckedPosition(-1);
        setAnalystCheckedPosition(-1);
        setViewCheckedPosition(-1);
    }

    private int stockCheckedPosition = -1, analystCheckedPosition = -1, viewCheckedPosition = -1;
    // End Filter positions set up
}
