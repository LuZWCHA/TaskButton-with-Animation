package com.lu.indexpagedemo.presenter;
import android.util.Log;

import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.DesingerContract;
import com.lu.indexpagedemo.model.DesingerModelImpl;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.base.rxjava.Notifys.MyNotifys;
import com.lu.indexpagedemo.base.rxjava.RxBus;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
* Created by 陆正威 on 2017/04/06
*/

public class DesingerPresenterImpl extends BasePresenterImpl<DesingerContract.View>{

    private DesingerContract.Model model;

    public DesingerPresenterImpl() {
        super();
        model = new DesingerModelImpl();
    }

    public void updateData(int page) {
        model.getDesiners(page).subscribe(new Observer<PagesPickerBean<IBaseBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Utils.MakeTost(false,"designer");
            }

            @Override
            public void onNext(PagesPickerBean<IBaseBean> value) {
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

    public void refresh(){
        model.getDesiners(1).subscribe(new Observer<PagesPickerBean<IBaseBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Utils.MakeTost(false,"designer");
                Log.e("Designer","update");
            }

            @Override
            public void onNext(PagesPickerBean<IBaseBean> value) {
                getView().refreshData(value);
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

}