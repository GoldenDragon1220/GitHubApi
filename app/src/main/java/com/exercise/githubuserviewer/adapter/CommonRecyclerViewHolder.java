package com.exercise.githubuserviewer.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by rexhuang on 2018/9/29.
 */
public class CommonRecyclerViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> mViews = new SparseArray<>();
    public int viewType;

    public CommonRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    public CommonRecyclerViewHolder(View itemView, int viewType) {
        super(itemView);
        this.viewType = viewType;
    }

    public <T extends View> T getView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        return (T) view;
    }
}