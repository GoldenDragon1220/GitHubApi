package com.exercise.githubuserviewer.github.interactors;

import com.exercise.githubuserviewer.github.repository.GitHubApiRepository;
import com.exercise.githubuserviewer.github.repository.IGitHubApiRepository;

import io.reactivex.Single;

/**
 * Created by rexhuang on 2018/9/28.
 */

public class GetUserListUseCase extends BaseUseCase {

    private int mPage;
    private int mPerPage;
    private int mSince;
    private IGitHubApiRepository mRepository;

    public GetUserListUseCase(int page, int perPage, int since) {
        mPage = page;
        mPerPage = perPage;
        mSince = since;
        mRepository = new GitHubApiRepository();
    }

    @Override
    public Single buildObservable() {
        return mRepository.getUserList(mPage, mPerPage, mSince);
    }
}
