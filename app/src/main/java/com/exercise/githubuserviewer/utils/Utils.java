package com.exercise.githubuserviewer.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.exercise.githubuserviewer.api.BaseApiException;
import com.exercise.githubuserviewer.github.apiservice.GitHubApiException;

/**
 * Created by rexhuang on 2018/9/29.
 */

public class Utils {

    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(colorId));
        }
    }

    public static String getErrorMessage(@NonNull Throwable throwable) {
        String errorMsg = "";

        if (throwable instanceof GitHubApiException ||
                throwable instanceof BaseApiException) {
            errorMsg = throwable.getMessage();
        } else if (throwable.getCause() != null ||
                throwable.getCause() instanceof GitHubApiException ||
                throwable.getCause() instanceof BaseApiException) {
            errorMsg = throwable.getCause().getMessage();
        } else if (!TextUtils.isEmpty(throwable.getMessage())) {
            errorMsg = throwable.getMessage();
        }

        return errorMsg;

    }

    public static boolean isNetworkAvailable(Context cxt) {
        if(cxt == null){
            return false;
        }
        Context context = cxt instanceof Activity ? cxt.getApplicationContext() : cxt;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
