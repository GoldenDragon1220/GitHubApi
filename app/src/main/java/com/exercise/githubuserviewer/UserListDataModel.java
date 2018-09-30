package com.exercise.githubuserviewer;

import com.exercise.githubuserviewer.github.bean.GitHubUsersBean;

import java.util.List;

/**
 * Created by rexhuang on 2018/9/29.
 */

public class UserListDataModel {

    private List<GitHubUsersBean> mUserList;
    private boolean hasData = true;

    public List<GitHubUsersBean> getUserList() {
        return mUserList;
    }

    public void setUserList(List<GitHubUsersBean> mUserList) {
        this.mUserList = mUserList;
    }

    public boolean hasMoreData() {
        return hasData;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }
}
