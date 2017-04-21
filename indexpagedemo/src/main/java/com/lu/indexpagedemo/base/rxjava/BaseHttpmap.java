package com.lu.indexpagedemo.base.rxjava;

import com.lu.indexpagedemo.bean.base.HttpResponseBase;

import io.reactivex.functions.Function;

/**
 * Created by 陆正威 on 2017/4/6.
 */

public class BaseHttpmap<T> implements Function<HttpResponseBase<T>,T> {

    @Override
    public T apply(HttpResponseBase<T> tHttpResponseBase) throws Exception {
        if(tHttpResponseBase.getState()>= 0)
        {
            return tHttpResponseBase.getData();
        }else{
            throw new Exception(tHttpResponseBase.getMessage());
        }
    }
}
