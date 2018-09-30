package com.exercise.githubuserviewer;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.exercise.githubuserviewer.adapter.UserDetailListViewAdapter;
import com.exercise.githubuserviewer.api.Resources;
import com.exercise.githubuserviewer.github.bean.UserDetailInfo;
import com.exercise.githubuserviewer.glide.GlideApp;
import com.exercise.githubuserviewer.utils.Utils;
import com.exercise.githubuserviewer.view.CircleImageView;
import com.exercise.githubuserviewer.viewmodel.UserDetailInfoViewModel;

/**
 * Created by rexhuang on 2018/9/29.
 */

public class UserDetailInfoActivity extends AppCompatActivity {

    private static final String TAG = "UserDetailInfoActivity";
    static final String KEY_LOGIN_NAME = "login_name";
    private CircleImageView mAvatarIv;
    private ListView mListView;
    private TextView mNameTv;
    private TextView mEmojiTv;
    private TextView mErrorMsgTv;
    private View mProgressBar;
    private View mErrorTips;
    private UserDetailListViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_detail_activity);
        initView();
        initModel();
        Utils.setStatusBarColor(this, R.color.grey_100);
    }

    private void initView() {
        mAvatarIv = findViewById(R.id.user_detail_avatar);
        mNameTv = findViewById(R.id.user_detail_name);
        mProgressBar = findViewById(R.id.user_detail_progress_bar);
        mEmojiTv = findViewById(R.id.user_detail_bio);
        mErrorTips = findViewById(R.id.user_detail_error_tips);
        mErrorMsgTv = findViewById(R.id.error_tips_text);
        mAdapter = new UserDetailListViewAdapter(this, (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        mListView = findViewById(R.id.user_detail_information);
        mListView.setAdapter(mAdapter);

        findViewById(R.id.user_detail_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initModel() {
        UserDetailInfoViewModel viewModel = ViewModelProviders.of(this).get(UserDetailInfoViewModel.class);
        viewModel.getUserDetailInfoLivedata().observe(this, new Observer<Resources<UserDetailInfo>>() {
            @Override
            public void onChanged(@Nullable Resources<UserDetailInfo> resources) {
                if (resources != null) {
                    if (resources.mStatus == Resources.SUCCESS) {
                        mAdapter.setData(resources.mData);
                        updateUserInfo(resources.mData.getAvatar_url(),
                                resources.mData.getName(),
                                resources.mData.getBio());
                    }
                    updateCommonUI(resources);
                }

            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra(KEY_LOGIN_NAME);
        Log.d(TAG, "Request name: " + name);
        if (!TextUtils.isEmpty(name)) {
            viewModel.requsetUserDetailInfo(name);
        }
    }

    private void updateUserInfo(String avatarUrl, String userName, String bio) {
        if (!TextUtils.isEmpty(userName)) {
            mNameTv.setText(userName);
        }

        if (!TextUtils.isEmpty(bio)) {
            mEmojiTv.setText(bio);
        } else {
            mEmojiTv.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(avatarUrl)) {
            GlideApp.with(this)
                    .load(avatarUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.default_avatar)
                    .into(mAvatarIv);
        } else {
            mAvatarIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_avatar));
        }

    }

    private void updateCommonUI(@NonNull Resources resources) {
        switch (resources.mStatus) {
            case Resources.SUCCESS:
                mProgressBar.setVisibility(View.GONE);
                mErrorTips.setVisibility(View.GONE);
                break;
            case Resources.ERROR:
                mProgressBar.setVisibility(View.GONE);
                mErrorTips.setVisibility(View.VISIBLE);
                if (resources.mException != null) {
                    if (!Utils.isNetworkAvailable(getApplicationContext())) {
                        mErrorMsgTv.setText("No network");
                    } else {
                        String errorMsg = Utils.getErrorMessage(resources.mException);
                        if (!TextUtils.isEmpty(errorMsg)) {
                            mErrorMsgTv.setText(errorMsg);
                        }
                    }
                }
                break;
            case Resources.LOADING:
                mProgressBar.setVisibility(View.VISIBLE);
                mErrorTips.setVisibility(View.GONE);
                break;
        }
    }
}
