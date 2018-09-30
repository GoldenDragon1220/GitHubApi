package com.exercise.githubuserviewer.api;

import android.text.TextUtils;
import android.util.Log;

import com.exercise.githubuserviewer.github.bean.ErrorMessageBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.exceptions.Exceptions;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rexhuang on 2018/9/28.
 */

public class BaseApiService {

    public <T> T initService(Class<T> serviceClass, String endPoint) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);

        //For debug purpose
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);

        OkHttpClient client = builder.build();

        return new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(serviceClass);
    }

    //handle http error.
    public <T> void parseApiResponse(Response<T> response) {
        if (response == null) {
            Exceptions.propagate(new BaseApiException("Incorrect response"));
        }

        if (response.code() == 200) {
            if (response.body() == null) {
                BaseApiException exception = new BaseApiException("Response body is null");
                Exceptions.propagate(exception);
            }
        } else {
            ErrorMessageBean errorMessageBean = null;
            String reason = "Http Error.";
            if (response.errorBody() != null) {
                try {
                    errorMessageBean = new Gson().fromJson(response.errorBody().string(), ErrorMessageBean.class);
                    if (!TextUtils.isEmpty(errorMessageBean.getMessage())) {
                        reason = errorMessageBean.getMessage();
                    }
                } catch (Exception e) { }
            }

            BaseApiException exception = new BaseApiException(reason);
            exception.setHttpErrorCode(response.code());
            Exceptions.propagate(exception);
        }
    }
}
