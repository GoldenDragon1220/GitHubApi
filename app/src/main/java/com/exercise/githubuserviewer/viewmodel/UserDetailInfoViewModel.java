package com.exercise.githubuserviewer.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.exercise.githubuserviewer.api.Resources;
import com.exercise.githubuserviewer.github.bean.UserDetailInfo;
import com.exercise.githubuserviewer.github.interactors.GetUserDetailUseCase;

import io.reactivex.functions.BiConsumer;

/**
 * Created by rexhuang on 2018/9/29.
 */

public class UserDetailInfoViewModel extends ViewModel {

    private MutableLiveData<Resources<UserDetailInfo>> mUserDetailInfoLiveData = new MutableLiveData<>();
    private GetUserDetailUseCase mUseCase;

    public MutableLiveData<Resources<UserDetailInfo>> getUserDetailInfoLivedata() {
        return mUserDetailInfoLiveData;
    }

    public void requestUserDetailInfo(String userName) {
        if (mUseCase != null) {
            mUseCase.dispose();
        }
        mUserDetailInfoLiveData.setValue(Resources.<UserDetailInfo>loading());
        mUseCase = new GetUserDetailUseCase(userName);
        mUseCase.execute(new BiConsumer<UserDetailInfo, Throwable>() {
            @Override
            public void accept(UserDetailInfo info, Throwable throwable) throws Exception {
                if (throwable == null) {
                    mUserDetailInfoLiveData.setValue(Resources.success(info));
                } else {
                    mUserDetailInfoLiveData.setValue(Resources.<UserDetailInfo>error(throwable));
                }
            }
        });
    }
}
