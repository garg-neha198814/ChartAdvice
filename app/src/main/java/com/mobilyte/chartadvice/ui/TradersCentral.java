package com.mobilyte.chartadvice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.mobilyte.chartadvice.Adapter.TradersCentralAdapter;
import com.mobilyte.chartadvice.Api.ApiHandler;
import com.mobilyte.chartadvice.Api.ApiResponse;
import com.mobilyte.chartadvice.Api.Apis;
import com.mobilyte.chartadvice.Modal.TradersCenterBean;
import com.mobilyte.chartadvice.Modal.TradersCenterData;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.image_package.FileUtils;
import com.mobilyte.chartadvice.image_package.ImageUtils;
import com.mobilyte.chartadvice.image_package.SampledImageCallback;
import com.mobilyte.chartadvice.interfaces.RetryInterface;
import com.mobilyte.chartadvice.ui.classes.BaseActivity;
import com.mobilyte.chartadvice.utility.Preferences;

import java.util.ArrayList;
import java.util.HashMap;

public class TradersCentral extends BaseActivity implements ApiResponse, RetryInterface, SampledImageCallback {
    //necessary variables
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TradersCentralAdapter adapter;
    private ImageView mfilter_button, marchives_button;
    private String stockValue = "", analystValue = "", viewValue = "";
    private Toolbar mToolbar;
    private RelativeLayout relative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traders_central);
        //intializing variable
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ImageView back = (ImageView) mToolbar.findViewById(R.id.back_button);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
       // mfilter_button = (ImageView) findViewById(R.id.filter_button);
        relative = (RelativeLayout) findViewById(R.id.parentPanel);
      //  marchives_button = (ImageView) findViewById(R.id.archives_button);
        relative = (RelativeLayout) findViewById(R.id.parentPanel);
        //setting button click
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //getting data from intent
        if (getIntent().getExtras() != null) {
            stockValue = getIntent().getExtras().get("stockSelection").toString();
            analystValue = getIntent().getExtras().get("analystSelection").toString();
            viewValue = getIntent().getExtras().get("viewSelection").toString();
            System.out.println(">>String >>>>>" + stockValue + analystValue + viewValue);
        }
        //intializing Recyler view
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        loadData();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.Archivefilter:
                Intent archivesActivity = new Intent(TradersCentral.this, Archives_Result.class);
                archivesActivity.putExtra("status","1");
                startActivity(archivesActivity);
                finish();
                break;
            case R.id.TraderCenterfilter:
                Intent filet = new Intent(TradersCentral.this, TradersCenter_Filter.class);
                startActivity(filet);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", Preferences.getInstance(this).getUserId());
        hashMap.put("product_name", getString(R.string.Traders_center));
        hashMap.put("token", getString(R.string.token_String));
        hashMap.put("current_date", "2016-03-21");
        hashMap.put("view_name", viewValue);
        hashMap.put("analyst_name", analystValue);
        hashMap.put("stock_name", stockValue);
        showDialog();
        ApiHandler.getInstance(TradersCentral.this).GetData(Apis.trader_url, TradersCentral.this, 1, hashMap, Apis.trader_central_tag);
    }

    //setting on sucess of api
    @Override
    public void onSuccess(String response, String tag) {
        hideDialog();
        if (tag.equalsIgnoreCase(Apis.trader_central_tag)) {
            Gson gson = new Gson();
            TradersCenterBean dataBean = gson.fromJson(response, TradersCenterBean.class);
            if (dataBean.getStatus() == 1) {
                adapter = new TradersCentralAdapter((ArrayList<TradersCenterData>) dataBean.getData(), this, this);
                mRecyclerView.setAdapter(adapter);
            } else {
                snackBarMessage(relative, response.toString());
            }
        }

    }

    // setting failure of api
    @Override
    public void onFailure(Throwable t, String tag) {
        t.printStackTrace();
        hideDialog();
        if (tag.equalsIgnoreCase(Apis.trader_central_tag)) {
            snackBarRetry(relative, tag, this);
        }

    }

    //on back press
    @Override
    public void onBackPressed() {
        Intent service = new Intent(this, ServicesActivity.class);
        startActivity(service);
        finish();
    }

    @Override
    public void onRetry(String tag) {
        switch (tag) {
            case Apis.trader_central_tag:
                loadData();
                break;
        }
    }

    public void openChooser() {
        ImageUtils.getInstance().openImageChooser(this, this);
    }

    @Override
    public void onSampledImage(String base64, String path) {
        if (adapter != null) {
            adapter.showImage(base64, path);
        }
    }

    private String selectedImagePath = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageUtils.IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
            final boolean isCamera;
            if (data == null || data.getData() == null) {
                isCamera = true;
            } else {
                final String action = data.getAction();
                if (action == null) {
                    isCamera = false;
                } else {
                    isCamera = action.equals(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                }
            }

            if (isCamera) {
                selectedImagePath = ImageUtils.getInstance().getImagePath(this);
            } else {
                selectedImagePath = FileUtils.getPath(this, data.getData());
            }

            if ((selectedImagePath != null) && !(selectedImagePath.equals(""))) {
                // selectedImagePath is the path of selected or captured image.
                ImageUtils.getInstance().resampleImage(this, this, selectedImagePath);

            }
        }
    }

    public void showDialog() {
        super.showDialog();
    }

    public void hideDialog() {
        super.hideDialog();
    }

    public void snackBarMessage(String message) {
        super.snackBarMessage(relative, message);
    }
}