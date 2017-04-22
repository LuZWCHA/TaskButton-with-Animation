package com.lu.indexpagedemo.presenter;
import android.util.Log;

import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.WorksContract;
import com.lu.indexpagedemo.model.WorksModelImpl;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.base.rxjava.Notifys.MyNotifys;
import com.lu.indexpagedemo.base.rxjava.RxBus;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
* Created by 陆正威 on 2017/04/10
*/

public class WorksPresenterImpl extends BasePresenterImpl<WorksContract.View>{
    private WorksContract.Model model;

    public WorksPresenterImpl() {
        super();
        model = new WorksModelImpl();
    }

    public void updateData(int page) {

        model.getWorks(page).subscribe(new Observer<PagesPickerBean<IBaseBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Utils.MakeTost(false,"works");
                Log.e("Work","update");
            }

            @Override
            public void onNext(PagesPickerBean<IBaseBean> value) {
                getView().updateList(value);
            }

            @Override
            public void onError(Throwable e) {
                Utils.MakeTost(true,"数据更新失败！");
                getView().loadMoreFail();
                EventBus.getDefault().post(new MyNotifys.UpdateFinishNotify());
            }

            @Override
            public void onComplete() {
                EventBus.getDefault().post(new MyNotifys.UpdateFinishNotify());
            }
        });
    }

    public void refreshData(){
        model.getWorks(1).subscribe(new Observer<PagesPickerBean<IBaseBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                //Utils.MakeTost(false,"works");
            }

            @Override
            public void onNext(PagesPickerBean<IBaseBean> value) {
                getView().updateList(value);
            }

            @Override
            public void onError(Throwable e) {
                Utils.MakeTost(true,"数据更新失败！");
                EventBus.getDefault().post(new MyNotifys.UpdateFinishNotify());
            }

            @Override
            public void onComplete() {
                EventBus.getDefault().post(new MyNotifys.UpdateFinishNotify());
            }
        });
    }

    public void initizData(){

    }

    @Override
    public void start() {

    }
}