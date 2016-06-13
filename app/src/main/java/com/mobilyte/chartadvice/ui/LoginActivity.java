package com.mobilyte.chartadvice.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobilyte.chartadvice.Api.ApiHandler;
import com.mobilyte.chartadvice.Api.ApiResponse;
import com.mobilyte.chartadvice.Api.Apis;
import com.mobilyte.chartadvice.ui.classes.BaseActivity;
import com.mobilyte.chartadvice.Modal.LoginDataBean;
import com.mobilyte.chartadvice.R;
import com.mobilyte.chartadvice.utility.Preferences;
import com.mobilyte.chartadvice.utility.Session;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements ApiResponse, View.OnClickListener {

    //necessary variables
    private Context mContext;
    private EditText mUserName, mPassword;
    private Button mEnterBTN;
    private TextView mErrorUserName, mErrorPassword;
    private HashMap<String, String> hashmap;
    private RelativeLayout relative;
    private Session session;//global variable
    private String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        relative = (RelativeLayout) findViewById(R.id.parent);
        mContext = LoginActivity.this;
        session = new Session(this);
        initview();
    }

    private void initview() {
        mUserName = (EditText) findViewById(R.id.login_username);
        mPassword = (EditText) findViewById(R.id.login_password);
        mErrorUserName = (TextView) findViewById(R.id.error_username);
        mErrorPassword = (TextView) findViewById(R.id.error_password);
        mEnterBTN = (Button) findViewById(R.id.login_enter);
        mEnterBTN.setOnClickListener(this);
    }

    // on sucess for api
    @Override
    public void onSuccess(String response, String tag) {
        hideDialog();
        try {
            if (tag.equalsIgnoreCase(Apis.loginTag)) {
                Gson gson = new Gson();
                LoginDataBean dataBean = gson.fromJson(response, LoginDataBean.class);
                if (dataBean.getStatus() == 1) {
                    username=mUserName.getText().toString();
                    password=mPassword.getText().toString();
                    session.setusername(username);
                    session.setpassword(password);
                    Intent servicesactivity = new Intent(LoginActivity.this, ServicesActivity.class);
                    String user = dataBean.getDataLogin().getUserId().toString();
                    Preferences.getInstance(this).storeUSerId(user);

                    int Query = Integer.parseInt(dataBean.getDataLogin().getQueryLimit());
                    Preferences.getInstance(this).storequery_limit(Query);
                    startActivity(servicesactivity);

                    finish();
                } else if (dataBean.getStatus() == 2) {
                    username=mUserName.getText().toString();
                    password=mPassword.getText().toString();
                    session.setusername(username);
                    session.setpassword(password);
                    Intent defaultactivity = new Intent(LoginActivity.this, DefaultUser.class);
                    startActivity(defaultactivity);
                    finish();
                } else {
                    showToast(response.toString());
                }

            }
        } catch (Exception e) {
            showToast(getString(R.string.checkuserpassword));
        }
    }

    //on failure of api
    @Override
    public void onFailure(Throwable t, String tag) {
        hideDialog();
        switch (tag) {
            default:
                snackBarMessage(relative, getString(R.string.internet_connection_error));
                break;
        }
    }

    //clcik submit button
    @Override
    public void onClick(View v) {
        if (v == mEnterBTN) {
            if (mUserName.getText().toString().length() == 0) {
                mErrorUserName.setVisibility(View.VISIBLE);
                mErrorUserName.setText("Please enter Username or Email");
            } else if (mPassword.getText().toString().length() == 0) {
                mErrorPassword.setVisibility(View.VISIBLE);
                mErrorPassword.setText("Password enter the password");
            } else {
                mErrorUserName.setVisibility(View.GONE);
                mErrorPassword.setVisibility(View.GONE);
                hashmap = new HashMap<>();
                hashmap.put("username", mUserName.getText().toString());
                hashmap.put("password", mPassword.getText().toString());
                hashmap.put("token", getString(R.string.token_String));
                showDialog();
                ApiHandler.getInstance(mContext).GetData(Apis.login, LoginActivity.this, 1, hashmap, Apis.loginTag);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
