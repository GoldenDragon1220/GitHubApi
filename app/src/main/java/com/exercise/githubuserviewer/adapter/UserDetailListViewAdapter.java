package com.exercise.githubuserviewer.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exercise.githubuserviewer.R;
import com.exercise.githubuserviewer.github.bean.UserDetailInfo;

/**
 * Created by rexhuang on 2018/9/29.
 */

public class UserDetailListViewAdapter extends BaseAdapter {

    //1. login, site_admin (badge)
    //2. location
    //3. blog
    private final static int ITEM_COUNT = 3;
    private final static int ITEM_LOGIN_NAME = 0;
    private final static int ITEM_LOCATION = 1;
    private final static int ITEM_BLOG = 2;
    private UserDetailInfo mData;
    private LayoutInflater mInflater;
    private Context mContext;

    public UserDetailListViewAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
    }

    public void setData(UserDetailInfo data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData != null ? ITEM_COUNT : 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        VH vh;
        if (view == null) {
            view = mInflater.inflate(R.layout.layout_user_detail_info_item, viewGroup, false);
            vh = new VH();
            vh.mAvatarIv =  view.findViewById(R.id.detail_item_icon_iv);
            vh.mTitleTv = view.findViewById(R.id.detail_item_title);
            vh.mBadgeTv = view.findViewById(R.id.detail_item_site_admin);
            view.setTag(vh);
        } else {
            vh = (VH)view.getTag();
        }

        switch (position) {
            case ITEM_LOGIN_NAME:
                vh.mAvatarIv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_avatar));
                vh.mTitleTv.setText(mData.getLogin());
                vh.mTitleTv.setTextColor(mContext.getResources().getColor(R.color.black));
                if (mData.isSite_admin()) {
                    vh.mBadgeTv.setVisibility(View.VISIBLE);
                } else {
                    vh.mBadgeTv.setVisibility(View.GONE);
                }
                break;
            case ITEM_LOCATION:
                vh.mAvatarIv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_location));
                if (TextUtils.isEmpty(mData.getLocation())) {
                    vh.mTitleTv.setText("Unknown");
                } else {
                    vh.mTitleTv.setText(mData.getLocation());
                }
                vh.mBadgeTv.setVisibility(View.GONE);
                break;
            case ITEM_BLOG:
                vh.mAvatarIv.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_link));
                if (TextUtils.isEmpty(mData.getBlog())) {
                    vh.mTitleTv.setText("None");
                } else {
                    vh.mTitleTv.setText(mData.getBlog());
                    vh.mTitleTv.setTextColor(mContext.getResources().getColor(R.color.blue_500));
                }
                vh.mBadgeTv.setVisibility(View.GONE);
                break;
        }


        return view;
    }

    private static class VH {
        ImageView mAvatarIv;
        TextView mTitleTv;
        TextView mBadgeTv;
    }

}
