package com.exercise.githubuserviewer.api;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by rexhuang on 2018/9/29.
 */

public class Resources<T> {

    public static final int LOADING = 0;
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    @IntDef({LOADING, SUCCESS, ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {}

    @Status
    public final int mStatus;
    public final T mData;
    public final Throwable mException;

    public Resources(@Status int status, T data,Throwable exception) {
        mStatus = status;
        mData = data;
        mException = exception;
    }

    public static <T> Resources<T> loading() {
        return new Resources<>(LOADING, null, null);
    }

    public static <T> Resources<T> success(T data) {
        return new Resources(SUCCESS, data, null);
    }

    public static <T> Resources<T> error(Throwable throwable) {
        return new Resources<>(ERROR, null, throwable);
    }
}
