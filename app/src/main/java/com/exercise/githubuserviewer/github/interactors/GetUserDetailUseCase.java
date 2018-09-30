package com.exercise.githubuserviewer.github.interactors;

import com.exercise.githubuserviewer.github.repository.GitHubApiRepository;
import com.exercise.githubuserviewer.github.repository.IGitHubApiRepository;

import io.reactivex.Single;

/**
 * Created by rexhuang on 2018/9/28.
 */

public class GetUserDetailUseCase extends BaseUseCase {

    private final IGitHubApiRepository mRepository;
    private final String mName;

    public GetUserDetailUseCase(String name) {
        mRepository = new GitHubApiRepository();
        mName = name;
    }

    @Override
    public Single buildObservable() {
        return mRepository.getUserDetailInfo(mName);
    }
}
