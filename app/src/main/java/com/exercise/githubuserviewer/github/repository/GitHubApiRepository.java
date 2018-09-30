package com.exercise.githubuserviewer.github.repository;

import com.exercise.githubuserviewer.github.apiservice.GitHubApiException;
import com.exercise.githubuserviewer.github.apiservice.GitHubApiService;
import com.exercise.githubuserviewer.github.bean.GitHubUsersBean;
import com.exercise.githubuserviewer.github.bean.UserDetailInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import retrofit2.Response;

/**
 * Created by rexhuang on 2018/9/28.
 */

public class GitHubApiRepository implements IGitHubApiRepository {

    private GitHubApiService mService;

    public GitHubApiRepository() {
        mService = GitHubApiService.getInstance();
    }

    @Override
    public Single<List<GitHubUsersBean>> getUserList(int page, int perPage, int since) {
        return mService.getUserList(page, perPage, since)
                .map(new Function<Response<JsonArray>, List<GitHubUsersBean>>() {
                    @Override
                    public List<GitHubUsersBean> apply(Response<JsonArray> jsonObjectResponse) throws Exception {
                        return new Gson().fromJson(jsonObjectResponse.body(),
                                new TypeToken<ArrayList<GitHubUsersBean>>(){}.getType());
                }
                }).singleOrError();
    }

    @Override
    public Single<UserDetailInfo> getUserDetailInfo(String name) {
        return mService.getUserInfo(name)
                .map(new Function<Response<JsonObject>, UserDetailInfo>() {
                    @Override
                    public UserDetailInfo apply(Response<JsonObject> jsonObjectResponse) throws Exception {
                        return new Gson().fromJson(jsonObjectResponse.body(), UserDetailInfo.class);
                    }
                }).singleOrError();
    }
}
