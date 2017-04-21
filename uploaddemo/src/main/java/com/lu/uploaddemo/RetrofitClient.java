package com.lu.uploaddemo;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by 陆正威 on 2017/4/5.
 */

public class RetrofitClient {

    public static long DEFAULT_TIMEOUT = 10;
    public static String BASE_URL = "http://www.lhbzimo.cn/";
    private TestApi mTsetApi;
    private Retrofit mRetrofit;
    private static RetrofitClient mInstance;

    private RetrofitClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("okhttp3",message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY));
         mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mTsetApi = mRetrofit.create(TestApi.class);
    }

    public static RetrofitClient getInstance()
    {
        if(mInstance == null)
        {
            synchronized (RetrofitClient.class) {
                mInstance = new RetrofitClient();
            }
        }
        return mInstance;
    }

    public TestApi getApi()
    {
        return mTsetApi;
    }

    public Retrofit getRetrofit()
    {
        return mRetrofit;
    }
}
