package com.exercise.githubuserviewer.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.exercise.githubuserviewer.UserListDataModel;
import com.exercise.githubuserviewer.api.Resources;
import com.exercise.githubuserviewer.github.bean.GitHubUsersBean;
import com.exercise.githubuserviewer.github.interactors.GetUserListUseCase;

import java.util.List;

import io.reactivex.functions.BiConsumer;

/**
 * Created by rexhuang on 2018/9/29.
 */

public class UserListViewModel extends ViewModel {

    private MutableLiveData<Resources<UserListDataModel>> mUserListLifeData
            = new MutableLiveData<>();
    private static final int PER_PAGE = 20;
    private int currentPage = 0;
    private int lastId = 0;
    private GetUserListUseCase mUseCase;

    public MutableLiveData<Resources<UserListDataModel>> getUserListLiveData() {
        return mUserListLifeData;
    }

    public void requestGitHubUserListNextPage() {
        mUserListLifeData.setValue(Resources.<UserListDataModel>loading());
        currentPage++;

        if (mUseCase != null) {
            mUseCase.dispose();
        }

        mUseCase = new GetUserListUseCase(currentPage, PER_PAGE, lastId);
        mUseCase.execute(new BiConsumer<List<GitHubUsersBean>, Throwable>() {
            @Override
            public void accept(List<GitHubUsersBean> userListBean, Throwable throwable) throws Exception {
                if (throwable == null) {
                    UserListDataModel dataModel = new UserListDataModel();
                    dataModel.setUserList(userListBean);

                    if (userListBean.size() < PER_PAGE) {
                        dataModel.setHasData(false);
                    } else {
                        dataModel.setHasData(true);
                    }

                    lastId = userListBean.get(userListBean.size()-1).getId();

                    mUserListLifeData.setValue(Resources.success(dataModel));
                } else {
                    mUserListLifeData.setValue(Resources.<UserListDataModel>error(throwable));
                }
            }
        });
    }

    public void reload() {
        currentPage = 0;
        lastId = 0;
        requestGitHubUserListNextPage();
    }

}
