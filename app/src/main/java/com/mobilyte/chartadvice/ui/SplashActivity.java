package com.mobilyte.chartadvice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.mobilyte.chartadvice.ui.classes.BaseActivity;
import com.mobilyte.chartadvice.utility.Session;

public class SplashActivity extends BaseActivity {
    //necessary variables
    private Handler mHandler;
    private Runnable mRunnable;
    private static final long SPLASH_DURATION = 2500L;
    private Session session;//global variable

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        session = new Session(this);

        String username=session.getusername();
        String password=session.getpassword();
        Log.e("User name","User name"+username);
        Log.e("Password", "Password" + password);

        if(!((username.equals("")||username=="") & (password.equals("")||password=="")))
        {

            Intent servicesactivity = new Intent(this, ServicesActivity.class);
            startActivity(servicesactivity);
            finish();
        }


        mRunnable = new Runnable() {
            @Override
            //setting intent to login
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
       // SystemClock.sleep(TimeUnit.SECONDS.toMillis(2));
    }
    //on resume
    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, SPLASH_DURATION);
    }
     //on pause
    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }
}
