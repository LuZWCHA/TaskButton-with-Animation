package com.lu.indexpagedemo.base.rxjava;

import android.util.Log;

import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.base.HttpResponseBase;

import io.reactivex.Observer;

/**
 * Created by 陆正威 on 2017/4/6.
 */

public abstract class BasehttpObserver<T> implements Observer<HttpResponseBase<T>> {
    private static final String TAG = "BasehttpObserver";

    @Override
    public void onNext(HttpResponseBase<T> value) {
        if (value.isSucceful()) {
            T t = value.getData();
            onHandleSuccess(t);
        } else {
            onHandleError(value.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "error:" + e.toString());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
    }

    protected abstract void onHandleSuccess(T t);

    protected void onHandleError(String msg) {
        Utils.MakeTost(true,msg);
    }
}
