package com.exercise.githubuserviewer.github.apiservice;

import android.util.Log;

import com.exercise.githubuserviewer.api.BaseApiService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by rexhuang on 2018/9/28.
 */

public class GitHubApiService extends BaseApiService {

    private final static String TAG = "GitHubApiService";
    private final static String END_POINT = "https://api.github.com/";
    private final static String QUERY_KEY_PAGE = "page";
    private final static String QUERY_KEY_PER_PAGE = "per_page";
    private final static String QUERY_KEY_SINCE = "since";
    private static class LazyHolder {
        static final GitHubApiService INSTANCE = new GitHubApiService();
    }

    public static GitHubApiService getInstance() {
        return LazyHolder.INSTANCE;
    }

    //member fields
    private IGitHubApiService mService;

    private GitHubApiService() {
        mService = initService(IGitHubApiService.class, END_POINT);
    }

    public Observable<Response<JsonArray>> getUserList(int page, int perPage, int since) {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put(QUERY_KEY_PAGE, String.valueOf(page));
        queryMap.put(QUERY_KEY_PER_PAGE, String.valueOf(perPage));
        queryMap.put(QUERY_KEY_SINCE, String.valueOf(since));

        return mService.getUserList(queryMap)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Response<JsonArray>>() {
                    @Override
                    public void accept(Response<JsonArray> jsonObjectResponse) throws Exception {
                        parseApiResponse(jsonObjectResponse);
                    }
                });
    }

    public Observable<Response<JsonObject>> getUserInfo(String userName) {
        return mService.getUserInfo(userName)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Response<JsonObject>>() {
                    @Override
                    public void accept(Response<JsonObject> jsonObjectResponse) throws Exception {
                        parseApiResponse(jsonObjectResponse);
                    }
                });
    }
}
