package com.mobilyte.chartadvice.Api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by neha.garg on 5/27/2016.
 */
public class NiftyEdgeHandler {
    // intializing all necessary variables
    public static NiftyEdgeHandler instance;
    public static Context context;
    RequestQueue requestQueue;

    //getting context from nifty edge activity and intializing request queue
    public NiftyEdgeHandler(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    //
    public static NiftyEdgeHandler getInstance(Context context) {
        if (instance == null) {
            instance = new NiftyEdgeHandler(context);
        }
        return instance;
    }

    //checking the api is of post or get method
    public void methodGet(String url, final ApiResponse apiResponse, int type) {
        if (type == 0) {
            type = Request.Method.GET;
        } else {
            type = Request.Method.POST;
        }

        //storing the api data after hit in a string variable(stringRequest)
        StringRequest stringRequest = new StringRequest(type, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                apiResponse.onSuccess(response);//sending data into interface onsuccess method

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        apiResponse.onFailure(error);//if fail to get data error report send into interface onfailure method

                    }
                });

        requestQueue.add(stringRequest);
    }

    // interface storing api response
    public interface ApiResponse {
        public void onSuccess(String response);

        public void onFailure(Throwable t);

    }
}
