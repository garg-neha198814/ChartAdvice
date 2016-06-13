package com.mobilyte.chartadvice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilyte.chartadvice.Api.Apis;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.interfaces.RetryInterface;
import com.mobilyte.chartadvice.ui.DefaultUserDescription;
import com.mobilyte.chartadvice.ui.classes.BaseActivity;
import com.mobilyte.chartadvice.utility.Session;

public class DefaultUser extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    //setting variable
    private LinearLayout mNifty, mFuture, mOption, mEquity, mTrader;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView toolbarTitle;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_user_navigation);

        mNifty = (LinearLayout) findViewById(R.id.nifty);
        mFuture = (LinearLayout) findViewById(R.id.future);
        mOption = (LinearLayout) findViewById(R.id.option);
        mEquity = (LinearLayout) findViewById(R.id.equlity);
        mTrader = (LinearLayout) findViewById(R.id.trader);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.findViewById(R.id.back_button).setVisibility(View.GONE);
//        toolbarTitle.setText("Services");
        setSupportActionBar(toolbar);

        mNifty.setOnClickListener(this);
        mFuture.setOnClickListener(this);
        mOption.setOnClickListener(this);
        mEquity.setOnClickListener(this);
        mTrader.setOnClickListener(this);

        session = new Session(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nifty:
                goToDefaultUserDescription(getString(R.string.niftyEdgeTitle));
                break;
            case R.id.future:
                goToDefaultUserDescription(getString(R.string.futureGainTitle));
                break;
            case R.id.option:
                goToDefaultUserDescription(getString(R.string.optionGainTitle));
                break;
            case R.id.equlity:
                goToDefaultUserDescription(getString(R.string.equityGainTitle));
                break;
            case R.id.trader:
                goToDefaultUserDescription(getString(R.string.traderCenterTitle));
                break;


        }
    }

    private void goToDefaultUserDescription(String title) {
        Intent userDesc = new Intent(this, DefaultUserDescription.class);
        userDesc.putExtra("title", title);
        startActivity(userDesc);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent service = new Intent(this, ServicesActivity.class);
        startActivity(service);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {

        int id = item.getItemId();
        item.setChecked(true);
        drawer.closeDrawers();

        if (id == R.id.dash_board_nav) {

            Intent intent = new Intent(this, ServicesActivity.class);
            startActivity(intent);
            finish();
            // Handle the camera action
        } else if (id == R.id.product_service_nav)
        {
            drawer.closeDrawers();
        }
        else if (id == R.id.about_us_nav)
        {
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

    }

