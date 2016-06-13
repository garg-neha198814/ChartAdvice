package com.mobilyte.chartadvice.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.mobilyte.chartadvice.R;

/**
 * Created by mohit on 6/6/16.
 */
public class Preferences {
    // intializing all necessary variables
    private static SharedPreferences sp;
    private static Preferences prefs;

    private Preferences() {

    }
    //settinf SharedPreferences for use
    public static Preferences getInstance(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(context.getString(R.string.app_name), context.MODE_PRIVATE);
        if (prefs == null)
            prefs = new Preferences();
        return prefs;
    }

    public int getQuery()
    {
        return sp.getInt("query",0);
    }

    public void storequery(int query)
    {
        sp.edit().putInt("query",query).apply();

    }
    public String getUserId() {
        return sp.getString("user_id", "");
    }

    public void storeUSerId(String user_id) {
        sp.edit().putString("user_id", user_id).apply();
    }

    public int getQuery_limit()
    {
        return sp.getInt("query_limit", 0);
    }

    public void storequery_limit(int query_limit) {
        sp.edit().putInt("query_limit", query_limit).apply();


    }
    }
