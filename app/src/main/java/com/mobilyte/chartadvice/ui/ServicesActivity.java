package com.mobilyte.chartadvice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobilyte.chartadvice.Adapter.ServicesAdapter;
import com.mobilyte.chartadvice.Api.ApiHandler;
import com.mobilyte.chartadvice.Api.ApiResponse;
import com.mobilyte.chartadvice.Api.Apis;
import com.mobilyte.chartadvice.Modal.ServiceModalData;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.interfaces.RetryInterface;
import com.mobilyte.chartadvice.ui.classes.BaseActivity;
import com.mobilyte.chartadvice.utility.Preferences;
import com.mobilyte.chartadvice.utility.Session;

import java.util.HashMap;


public class ServicesActivity extends BaseActivity implements ApiResponse, NavigationView.OnNavigationItemSelectedListener, RetryInterface {
    //necessary variables
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ServicesAdapter adapter;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView toolbarTitle;
    private Boolean exit = false;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_);

        //intializing variables
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        session = new Session(this);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Services");
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //intializing Recyler view
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //setting navigation drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        loadData();
    }

    // on back press of activity
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

    //navigation drawer click
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        item.setChecked(true);
        drawer.closeDrawers();

        if (id == R.id.dash_board_nav) {
            drawer.closeDrawer(GravityCompat.START);
            // Handle the camera action
        } else if (id == R.id.product_service_nav) {
            Intent intent = new Intent(this, DefaultUser.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.about_us_nav) {
            Intent intent = new Intent(this, Default_WebView.class);
            intent.putExtra("url", Apis.about_us_url);
            intent.putExtra("title", getString(R.string.aboutUsTitle));
            startActivity(intent);
            finish();
        }
        else if (id == R.id.logout_nav) {
            session.setusername("");
            session.setpassword("");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    // on sucess of api
    @Override
    public void onSuccess(String response, String tag) {
        hideDialog();
        if (tag.equalsIgnoreCase(Apis.serviceTag)) {
            Gson gson = new Gson();
            ServiceModalData dataBean = gson.fromJson(response, ServiceModalData.class);
            if (dataBean.getStatus() == 1) {
                adapter = new ServicesAdapter(dataBean, this);
                mRecyclerView.setAdapter(adapter);
            } else {
                snackBarMessage(findViewById(R.id.main), response.toString());
            }
        }
    }

    //on failure
    @Override
    public void onFailure(Throwable t, String tag) {
        hideDialog();
        if (tag.equalsIgnoreCase(Apis.serviceTag)) {
            snackBarRetry(findViewById(R.id.main), Apis.serviceTag, this);
        }
    }

    private void loadData() {
        showDialog();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("user_id", Preferences.getInstance(this).getUserId());
        hashMap.put("token", getString(R.string.token_String));
        ApiHandler.getInstance(ServicesActivity.this).GetData(Apis.service_url, this, 1, hashMap, Apis.serviceTag);
    }


    @Override
    public void onRetry(String tag) {
        switch (tag) {
            case Apis.serviceTag:
                loadData();
                break;
        }
    }



}
