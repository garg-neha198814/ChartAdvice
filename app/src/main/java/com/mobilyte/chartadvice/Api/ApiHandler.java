package com.mobilyte.chartadvice.Api;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 13/4/16.
 */
public class ApiHandler {
    // intializing all necessary variables

    public static ApiHandler instance;
    public static Context context;
    RequestQueue requestQueue;
    private ProgressDialog progressDialog;

    //getting context activity and intializing request queue
    public ApiHandler(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public static ApiHandler getInstance(Context context) {
        if (instance == null) {
            instance = new ApiHandler(context);
        }
        return instance;
    }
    //checking the api is of post or get method
    public void GetData(String url, final ApiResponse apiResponse, int type, final HashMap<String, String> hashMap, final String tag) {
        if (type == 0) {
            type = Request.Method.GET;
        } else {
            type = Request.Method.POST;
        }
        //storing the api data after hit in a string variable(stringRequest)
        StringRequest stringRequest = new StringRequest(type, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                apiResponse.onSuccess(response,tag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                apiResponse.onFailure(error,tag);
            }
        }) {

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.putAll(hashMap);
                return params;
            }

        };

            requestQueue.add(stringRequest);
        }


}
