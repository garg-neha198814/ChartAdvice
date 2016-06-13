package com.mobilyte.chartadvice.utility;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by root on 10/6/16.
 */

public class Session
{
    private SharedPreferences prefs;

//
    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusername(String username) {
        prefs.edit().putString("username", username).commit();

    }

    public String getusername() {
        String username = prefs.getString("username","");
        return username;
    }

    public void setpassword(String password) {
        prefs.edit().putString("password", password).commit();

    }

    public String getpassword() {
        String password = prefs.getString("password","");
        return password;
    }


}
