package com.mobilyte.chartadvice.interfaces;

import com.mobilyte.chartadvice.Modal.Querry_Bean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by mohit on 9/6/16.
 */
public interface GitHubClient {
    @Multipart
    @POST("query.php")
    Call<Querry_Bean> updateQuery(@PartMap Map<String, RequestBody> options, @Part MultipartBody.Part file) ;
}
