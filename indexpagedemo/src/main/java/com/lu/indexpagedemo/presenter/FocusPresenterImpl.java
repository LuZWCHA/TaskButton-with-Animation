package com.lu.indexpagedemo.presenter;

import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.FocusContract;
import com.lu.indexpagedemo.model.FocusModelImpl;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
* Created by 陆正威 on 2017/04/15
*/

public class FocusPresenterImpl extends BasePresenterImpl<FocusContract.View>{

    FocusContract.Model model;

    public FocusPresenterImpl(){
        model = new FocusModelImpl();
    }

    public void updateList()
    {
        model.getFocusfromNetWork()
        .subscribe(new Observer<List<DesignerBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<DesignerBean> value) {
                getView().updateList(value);
            }

            @Override
            public void onError(Throwable e) {
                getView().hideRefresh();
            }

            @Override
            public void onComplete() {
                getView().hideRefresh();
            }
        });
    }

}