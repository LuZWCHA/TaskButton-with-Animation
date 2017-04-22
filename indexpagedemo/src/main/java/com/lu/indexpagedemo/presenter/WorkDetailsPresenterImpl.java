package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;
import com.lu.indexpagedemo.bean.WorkBean;
import com.lu.indexpagedemo.contract.WorkDetailsContract;
import com.lu.indexpagedemo.model.WorkDetailsModelImpl;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
* Created by 陆正威 on 2017/04/21
*/

public class WorkDetailsPresenterImpl extends BasePresenterImpl<WorkDetailsContract.View>{
    WorkDetailsContract.Model model;

    public WorkDetailsPresenterImpl() {
        model = new WorkDetailsModelImpl();
    }

    public void getWorkBean(long id){

       Observable.concat(model.getWorkDetails(id),model.getUserbyWorkId(id))
       .subscribe(new Observer<Object>() {
           @Override
           public void onSubscribe(Disposable d) {
               getView().showLoading();
           }

           @Override
           public void onNext(Object value) {
                if(value instanceof WorkBean){
                    getView().updateDetails((WorkBean) value);
                }else {
                    getView().updateUser((User) value);
                }
           }

           @Override
           public void onError(Throwable e) {
                getView().hideLoading();
           }

           @Override
           public void onComplete() {
                getView().hideLoading();
           }
       });

    }
}