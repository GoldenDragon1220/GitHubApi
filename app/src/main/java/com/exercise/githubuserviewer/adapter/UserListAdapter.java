package com.exercise.githubuserviewer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.exercise.githubuserviewer.R;
import com.exercise.githubuserviewer.github.bean.GitHubUsersBean;
import com.exercise.githubuserviewer.glide.GlideApp;
import com.exercise.githubuserviewer.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rexhuang on 2018/9/29.
 */

public class UserListAdapter extends RecyclerView.Adapter {

    private static final String TAG = "UserListAdapter";

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_USER_ITEM = 2;
    private List<GitHubUsersBean> mData;
    private Context mContext;
    private AdapterListener mListener;
    private boolean mHasMoreData = true;

    public UserListAdapter(@NonNull Context context,@NonNull AdapterListener listener) {
        mContext = context;
        mListener = listener;
    }

    public void setData(List<GitHubUsersBean> data, boolean hasMoreData) {
        Log.d(TAG, "[setData] add " + data.size() + " data.");
        if (mData == null) {
            mData = new ArrayList<>();
        }

        mHasMoreData = hasMoreData;
        mData.addAll(data);
        notifyItemInserted(mData.size() - 1);
    }

    public boolean hasData() {
        return mData != null && mData.size() > 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new CommonRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_list_header,
                    parent, false));
        } else if (viewType == TYPE_FOOTER) {
            return new CommonRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_list_footer,
                    parent, false));
        } else {
            return new CommonRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_list_item,
                    parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != 0 && !isFooter(position)) {
            final GitHubUsersBean bean = mData.get(position - 1);

            CircleImageView avatarIv = ((CommonRecyclerViewHolder)holder).getView(R.id.avatar);
            String avatarUrl = bean.getAvatar_url();
            Log.d(TAG, "[load] url: " + avatarUrl);
            if (!TextUtils.isEmpty(avatarUrl)) {
                GlideApp.with(mContext)
                        .load(avatarUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(100, 100)
                        .placeholder(R.drawable.default_avatar)
                        .into(avatarIv);
            } else {
                avatarIv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.default_avatar));
            }

            TextView mLoginId = ((CommonRecyclerViewHolder)holder).getView(R.id.login_id);
            mLoginId.setText(bean.getLogin());

            View mBadget = ((CommonRecyclerViewHolder)holder).getView(R.id.site_admin);
            if (bean.isSite_admin()) {
                mBadget.setVisibility(View.VISIBLE);
            } else {
                mBadget.setVisibility(View.GONE);
            }

            ((CommonRecyclerViewHolder)holder).getView(R.id.user_item_root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked(bean.getLogin());
                }
            });

        } else if (isFooter(position)) {
            if (!mHasMoreData) {
                TextView footerTv = ((CommonRecyclerViewHolder)holder).getView(R.id.footer_text);
                footerTv.setText("No more data");
                ((CommonRecyclerViewHolder)holder).getView(R.id.footer_progress_bar).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() + 2 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (isFooter(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_USER_ITEM;
        }
    }

    public boolean isFooter(int position) {
        return position == getItemCount() - 1;
    }

    public interface AdapterListener {
        void onItemClicked(String loginName);
    }

}
