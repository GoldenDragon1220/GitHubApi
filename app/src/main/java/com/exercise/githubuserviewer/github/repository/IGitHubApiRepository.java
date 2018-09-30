package com.exercise.githubuserviewer.github.repository;

import com.exercise.githubuserviewer.github.bean.GitHubUsersBean;
import com.exercise.githubuserviewer.github.bean.UserDetailInfo;

import java.util.List;
import io.reactivex.Single;

/**
 * Created by rexhuang on 2018/9/28.
 */

public interface IGitHubApiRepository {

    Single<List<GitHubUsersBean>> getUserList(int page, int perPage, int since);
    Single<UserDetailInfo> getUserDetailInfo(String name);
}
