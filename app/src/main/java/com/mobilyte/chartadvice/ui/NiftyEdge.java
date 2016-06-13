package com.mobilyte.chartadvice.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.mobilyte.chartadvice.Adapter.NiftyEdgeAdapter;
import com.mobilyte.chartadvice.Api.Apis;
import com.mobilyte.chartadvice.Api.NiftyEdgeHandler;
import com.mobilyte.chartadvice.Modal.NiftyEdgeBean;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.interfaces.RetryInterface;
import com.mobilyte.chartadvice.ui.classes.BaseActivity;

public class NiftyEdge extends BaseActivity implements View.OnClickListener, NiftyEdgeHandler.ApiResponse, RetryInterface {

    //necessary variables
    private RecyclerView mRecyclerView;
    private NiftyEdgeAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private boolean loading = true;
    private int offset = 1, limit = 10;
    private int count = 10;
    private NiftyEdgeBean data;
    private ImageView mbackbutton;
    private Context mContext;
    int mPastItems, mVisibleCount, mTotalCount;
    private LinearLayout relative;
    private String DATA_URL = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nifty_edge);
        relative = (LinearLayout) findViewById(R.id.parentPanel);
        mContext = this;
        //intializing Recyler view
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mbackbutton=(ImageView)findViewById(R.id.back_button);
        mbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent(NiftyEdge.this, ServicesActivity.class);
                startActivity(service);
                finish();
            }
        });
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //implementing on scroll listener and hiting api again on every 10items
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) //check for scroll down
                {
                    mVisibleCount = mLayoutManager.getChildCount();
                    mTotalCount = mLayoutManager.getItemCount();
                    mPastItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if ((mVisibleCount + mPastItems) >= mTotalCount) {
                            loading = true;
                            count = count + 10;
                            DATA_URL = "http://www.chartadvise.com/web-services/nifty_edge.php?token=a152e84173914146e4bc4f391sd0f686ebc4f31&" + "product_name=Nifty_Edge&id=4&offset=" + offset + "&limit=" + limit;
                            loadData();
                        }
                    }
                }
            }
        });

        DATA_URL = "http://www.chartadvise.com/web-services/nifty_edge.php?token=a152e84173914146e4bc4f391sd0f686ebc4f31&" + "product_name=Nifty_Edge&id=4&offset=" + offset + "&limit=" + limit;
        //Sending necessary data to api handler class
        loadData();
    }

    private void loadData() {
        showDialog();
        NiftyEdgeHandler.getInstance(mContext).methodGet(DATA_URL, this, 0);
    }

    //getting data if it hits successfully
    @Override
    public void onSuccess(String response) {
        hideDialog();
        mRecyclerView.setVisibility(View.VISIBLE);
        Gson gson = new Gson();
        data = gson.fromJson(response, NiftyEdgeBean.class);

        if (adapter == null) {
            adapter = new NiftyEdgeAdapter(data.getArrayList(), this);
            mRecyclerView.setAdapter(adapter);
        } else {
            adapter.updatList(data.getArrayList());
        }
        loading = false;
    }


    //fail toast if data is not get
    @Override
    public void onFailure(Throwable t) {
        hideDialog();
        snackBarRetry(relative, Apis.nifty_tag, this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRetry(String tag) {
        switch (tag) {
            case Apis.nifty_tag:
                loadData();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent service = new Intent(this, ServicesActivity.class);
        startActivity(service);
        finish();
    }
}

