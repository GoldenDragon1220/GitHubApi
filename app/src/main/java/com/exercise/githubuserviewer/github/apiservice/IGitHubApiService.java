package com.exercise.githubuserviewer.github.apiservice;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by rexhuang on 2018/9/28.
 */

public interface IGitHubApiService {

    @GET("/users")
    Observable<Response<JsonArray>> getUserList(@QueryMap HashMap<String, String> map);

    @GET("/users/{user_name}")
    Observable<Response<JsonObject>> getUserInfo(@Path("user_name") String userName);

}
