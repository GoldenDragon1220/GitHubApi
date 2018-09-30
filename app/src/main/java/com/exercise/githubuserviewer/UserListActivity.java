package com.exercise.githubuserviewer;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.exercise.githubuserviewer.adapter.UserListAdapter;
import com.exercise.githubuserviewer.api.Resources;
import com.exercise.githubuserviewer.utils.Utils;
import com.exercise.githubuserviewer.viewmodel.UserListViewModel;

/**
 * Created by rexhuang on 2018/9/28.
 */

public class UserListActivity extends AppCompatActivity implements UserListAdapter.AdapterListener, View.OnClickListener{
    private static final String TAG = "UserListActivity";
    private RecyclerView mRecyclerView;
    private View mProgressBar;
    private View mReloadView;
    private TextView mErrorMsgView;
    private UserListAdapter mAdapter;
    private UserListViewModel mUserListViewModel;
    private boolean hasMoreData = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_list_activity);
        initView();
        initModel();
        Utils.setStatusBarColor(this, R.color.grey_900);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mReloadView = findViewById(R.id.error_tips);
        mErrorMsgView = findViewById(R.id.error_message);
        findViewById(R.id.reload).setOnClickListener(this);
        mAdapter = new UserListAdapter(this, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisiblePosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                    Log.d(TAG, "lastVisiblePosition: " + lastVisiblePosition);
                    if (mAdapter.isFooter(lastVisiblePosition)) {
                        onLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void initModel() {
        //use view model to control the data according to activity's life cycle.
        mUserListViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        mUserListViewModel.getUserListLiveData().observe(this, new Observer<Resources<UserListDataModel>>() {
            @Override
            public void onChanged(@Nullable Resources<UserListDataModel> resources) {
                if (resources != null) {
                    if (resources.mStatus == Resources.SUCCESS) {
                        hasMoreData = resources.mData.hasMoreData();
                        mAdapter.setData(resources.mData.getUserList(), hasMoreData);
                    }
                    updateCommonUI(resources, mAdapter.hasData());
                }
            }
        });
        mUserListViewModel.requestGitHubUserListNextPage();
    }

    public void onLoadMore() {
        if (hasMoreData) {
            mUserListViewModel.requestGitHubUserListNextPage();
        }
    }

    @Override
    public void onItemClicked(String loginName) {
        Log.d(TAG, "[onItemClicked] loginName: " + loginName);
        Intent intent = new Intent(this, UserDetailInfoActivity.class);
        intent.putExtra(UserDetailInfoActivity.KEY_LOGIN_NAME, loginName);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.reload:
                mUserListViewModel.reload();
                break;
        }
    }

    private void updateCommonUI(@NonNull Resources resources, boolean hasData) {
        switch (resources.mStatus) {
            case Resources.SUCCESS:
                mProgressBar.setVisibility(View.GONE);
                mReloadView.setVisibility(View.GONE);
                break;
            case Resources.ERROR:
                if (!hasData) {
                    mReloadView.setVisibility(View.VISIBLE);
                    if (resources.mException != null) {
                        if (!Utils.isNetworkAvailable(getApplicationContext())) {
                            mErrorMsgView.setText("No network.");
                        } else {
                            String errorMsg = Utils.getErrorMessage(resources.mException);
                            if (!TextUtils.isEmpty(errorMsg)) {
                                mErrorMsgView.setText(errorMsg);
                            }
                        }
                    }
                }
                mProgressBar.setVisibility(View.GONE);
                break;
            case Resources.LOADING:
                if (!hasData) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                mReloadView.setVisibility(View.GONE);
                break;
        }
    }
}
