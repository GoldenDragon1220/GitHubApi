package com.exercise.githubuserviewer.api;

/**
 * Created by rexhuang on 2018/9/28.
 */

public class BaseApiException extends Exception {

    public int mHttpErrorCode;

    public BaseApiException(String reason) {
        super(reason);
    }

    public void setHttpErrorCode(int errorCode) {
        mHttpErrorCode = errorCode;
    }

    public int getHttpErrorCode() {
        return mHttpErrorCode;
    }
}
