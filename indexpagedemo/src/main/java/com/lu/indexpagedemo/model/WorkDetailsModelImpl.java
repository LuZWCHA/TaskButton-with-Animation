package com.lu.indexpagedemo.model;
import com.lu.indexpagedemo.Api.ApiStore;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;
import com.lu.indexpagedemo.base.retrofit.RetrofitClient;
import com.lu.indexpagedemo.base.rxjava.BaseHttpmap;
import com.lu.indexpagedemo.base.rxjava.RxSchedulers;
import com.lu.indexpagedemo.bean.WorkBean;
import com.lu.indexpagedemo.bean.base.HttpResponseBase;
import com.lu.indexpagedemo.contract.WorkDetailsContract;

import io.reactivex.Observable;

/**
* Created by 陆正威 on 2017/04/21
*/

public class WorkDetailsModelImpl implements WorkDetailsContract.Model{

    @Override
    public Observable<WorkBean> getWorkDetails(long id) {
        ApiStore apiStore = RetrofitClient.getInstance().getRetrofit().create(ApiStore.class);

        return apiStore.getWorkBeanDetails(id)
                .compose(RxSchedulers.<HttpResponseBase<WorkBean>>compose())
                .map(new BaseHttpmap<WorkBean>());

    }

    @Override
    public Observable<User> getUserbyWorkId(long id) {
        ApiStore apiStore = RetrofitClient.getInstance().getRetrofit().create(ApiStore.class);

        return apiStore.getUserbyWorkId(id)
                .compose(RxSchedulers.<HttpResponseBase<User>>compose())
                .map(new BaseHttpmap<User>());
    }
}