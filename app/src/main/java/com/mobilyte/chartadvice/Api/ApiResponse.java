package com.mobilyte.chartadvice.Api;

/**
 * Created by root on 13/4/16.
 */
public interface ApiResponse {
    public void onSuccess(String response, String tag);
    public void onFailure(Throwable t, String tag);
}
