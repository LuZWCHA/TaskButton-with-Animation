package com.lu.indexpagedemo.base.retrofit;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 陆正威 on 2017/4/5.
 */

public class RetrofitClient {

    public static long DEFAULT_TIMEOUT = 10;
    public static String BASE_URL = "http://www.lhbzimo.cn/";
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
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

        mOkHttpClient = builder.build();
         mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
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
    private static Gson buildGson() {
        return new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                // 此处可以添加Gson 自定义TypeAdapter
                .create();
    }
    public Retrofit getRetrofit()
    {
        return mRetrofit;
    }
    public OkHttpClient getmOkHttpClient(){return mOkHttpClient;}
}
