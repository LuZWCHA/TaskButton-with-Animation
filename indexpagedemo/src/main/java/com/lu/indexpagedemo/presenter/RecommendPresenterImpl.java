package com.lu.indexpagedemo.presenter;

import android.util.Log;

import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.RecommendContract;
import com.lu.indexpagedemo.model.RecommendModelImpl;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.base.rxjava.Notifys.MyNotifys;
import com.lu.indexpagedemo.base.rxjava.RxBus;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 陆正威 on 2017/4/6.
 */

public class RecommendPresenterImpl extends BasePresenterImpl<RecommendContract.View> {

    private final static String TAG = "RecommendPresenterImpl";

    private RecommendContract.Model model;

    public RecommendPresenterImpl() {
        super();
        model = new RecommendModelImpl();
    }

    public void updateData(int page) {

        model.getRecommendWorksFromNetWork(page).subscribe(new Observer<PagesPickerBean<IBaseBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                //addCompositeDisposables(d);
            }

            @Override
            public void onNext(PagesPickerBean<IBaseBean> value) {
                Log.e("size",value.getData().size()+"");
                getView().updateList(value);
                if(!value.isNext())
                    getView().loadMoreEnd();
                else
                    getView().loadMoreComplete();
            }

            @Override
            public void onError(Throwable e) {
                Utils.MakeTost(true,"数据更新失败！");
                getView().loadMoreFaile();
            }

            @Override
            public void onComplete() {
                Utils.MakeTost(false,"已刷新！");
            }
        });
    }

    public void refreshData(){
        model.getRecommendWorksFromNetWork(1).subscribe(new Observer<PagesPickerBean<IBaseBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                //addCompositeDisposables(d);
                //Utils.MakeTost(false,"recommend");
            }

            @Override
            public void onNext(PagesPickerBean<IBaseBean> value) {
                getView().refreshList(value);
                if(!value.isNext())
                    getView().loadMoreEnd();
                else
                    getView().loadMoreComplete();
            }

            @Override
            public void onError(Throwable e) {
                Utils.MakeTost(true,"数据更新失败！");
                getView().loadMoreFaile();
                EventBus.getDefault().post(new MyNotifys.UpdateFinishNotify());
            }

            @Override
            public void onComplete() {
                Utils.MakeTost(false,"已刷新！");
                EventBus.getDefault().post(new MyNotifys.UpdateFinishNotify());
            }
        });
    }


    public void initizData(){

    }

    @Override
    public void start() {

    }

    public static String getTAG() {
        return TAG;
    }
}
