package com.lu.indexpagedemo.model;

import com.lu.indexpagedemo.Api.ApiStore;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.bean.ListShowBean;
import com.lu.indexpagedemo.bean.MidPicBean;
import com.lu.indexpagedemo.bean.MorePicBean;
import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.bean.base.HttpResponseBase;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.WorksContract;
import com.lu.indexpagedemo.base.retrofit.RetrofitClient;
import com.lu.indexpagedemo.base.rxjava.BaseHttpmap;
import com.lu.indexpagedemo.base.rxjava.RxSchedulers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;

/**
* Created by 陆正威 on 2017/04/10
*/

public class WorksModelImpl implements WorksContract.Model{

    @Override
    public Observable<PagesPickerBean<IBaseBean>> getWorks(int page) {

        final PagesPickerBean<IBaseBean> mWrappedPickerBean = new PagesPickerBean<>();
        List<IBaseBean> list = new ArrayList<>();

        ApiStore apiStore = RetrofitClient.getInstance().getRetrofit().create(ApiStore.class);
        Observable<HttpResponseBase<PagesPickerBean<ListShowBean>>> observer = apiStore.getWorkBeanList(page);

        return observer.compose(RxSchedulers.<HttpResponseBase<PagesPickerBean<ListShowBean>>>compose())
                .map(new BaseHttpmap<PagesPickerBean<ListShowBean>>())
                .flatMap(new Function<PagesPickerBean<ListShowBean>, ObservableSource<ListShowBean>>() {

                    @Override
                    public ObservableSource<ListShowBean> apply(PagesPickerBean<ListShowBean> pagesPickerBean) throws Exception {
                        mWrappedPickerBean.setNext(pagesPickerBean.isNext());
                        return Observable.fromIterable(pagesPickerBean.getData());
                    }
                })
                .map(new Function<ListShowBean, IBaseBean>() {
                    @Override
                    public IBaseBean apply(ListShowBean listShowBean) throws Exception {
                        listShowBean.setImage(Utils.AddBaseUrl(listShowBean.getImage()));
                        listShowBean.setAvatarPath(Utils.AddBaseUrl(listShowBean.getAvatarPath()));
                        return listShowBean;
                    }
                })
                .toList()
//                .collectInto(list, new BiConsumer<List<IBaseBean>, ListShowBean>() {
//                    @Override
//                    public void accept(List<IBaseBean> iBaseBeen, ListShowBean listShowBean) throws Exception {
//                        listShowBean.setImage(Utils.AddBaseUrl(listShowBean.getImage()));
//                        listShowBean.setAvatarPath(Utils.AddBaseUrl(listShowBean.getAvatarPath()));
//                        iBaseBeen.add(listShowBean);
//                    }
//                })
                .map(new Function<List<IBaseBean>, PagesPickerBean<IBaseBean>>() {
                    @Override
                    public PagesPickerBean<IBaseBean> apply(List<IBaseBean> iBaseBeen) throws Exception {
                        mWrappedPickerBean.setData(iBaseBeen);
                        return mWrappedPickerBean;
                    }
                }).toObservable();
    }
}