package com.lu.indexpagedemo.presenter;

import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.contract.IndexPageContract;
import com.lu.indexpagedemo.model.IndexPageModelImpl;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
* Created by 陆正威 on 2017/04/06
*/

public class IndexPagePresenterImpl extends BasePresenterImpl<IndexPageContract.View>{

    private IndexPageContract.Model model;

    public IndexPagePresenterImpl(){
        super();
        model = new IndexPageModelImpl();
    }

    public void updateRollPageView() {
        model.getRollViewPagerFromNetWork().subscribe(new Observer<ArrayList<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ArrayList<String> value) {
                getView().updateRollViewData(value);
            }

            @Override
            public void onError(Throwable e) {
                Utils.MakeTost(false,"获取出错");
                getView().updateOne();
            }

            @Override
            public void onComplete() {
                getView().updateOne();
            }
        });
    }

    public void initizRollPageView() {
        model.getRollViewPagerFromLocation().subscribe(new Observer<ArrayList<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ArrayList<String> value) {
                getView().updateRollViewData(value);
            }

            @Override
            public void onError(Throwable e) {
                Utils.MakeTost(false,"获取出错");
            }

            @Override
            public void onComplete() {
            }
        });
    }


    @Override
    public void start() {
    }
}