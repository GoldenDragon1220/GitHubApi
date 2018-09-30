package com.exercise.githubuserviewer.github.apiservice;

import com.exercise.githubuserviewer.api.BaseApiException;

/**
 * Created by rexhuang on 2018/9/28.
 */

public class GitHubApiException extends BaseApiException {

    public GitHubApiException(String reason) {
        super(reason);
    }
}
