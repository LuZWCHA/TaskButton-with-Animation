package com.lu.indexpagedemo.model;


import com.lu.indexpagedemo.Api.ApiStore;
import com.lu.indexpagedemo.bean.MidPicBean;
import com.lu.indexpagedemo.bean.MorePicBean;
import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.bean.RecommendBean;
import com.lu.indexpagedemo.bean.base.HttpResponseBase;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.RecommendContract;
import com.lu.indexpagedemo.base.retrofit.RetrofitClient;
import com.lu.indexpagedemo.base.rxjava.BaseHttpmap;
import com.lu.indexpagedemo.base.rxjava.RxSchedulers;
import com.lu.indexpagedemo.base.Tools.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;

/**
 * Created by 陆正威 on 2017/4/2.
 */

public class RecommendModelImpl implements RecommendContract.Model {

    public RecommendModelImpl() {

    }

    @Override
    public Observable<PagesPickerBean<IBaseBean>> getRecommendWorksFromLocation() {
        return null;
    }


    @Override
    public Observable<PagesPickerBean<IBaseBean>> getRecommendWorksFromNetWork(int page) {
        final List<IBaseBean> list = new ArrayList<>();
        MidPicBean midPicBean0 = new MidPicBean("https://images.pexels.com/photos/262372/pexels-photo-262372.jpeg?w=940&h=650&auto=compress&cs=tinysrgb", "ly");
        MidPicBean midPicBean1 = new MidPicBean("https://images.pexels.com/photos/262372/pexels-photo-262372.jpeg?w=940&h=650&auto=compress&cs=tinysrgb", "ly");
        MidPicBean midPicBean2 = new MidPicBean("https://images.pexels.com/photos/262372/pexels-photo-262372.jpeg?w=940&h=650&auto=compress&cs=tinysrgb", "ly");
        MidPicBean midPicBean3 = new MidPicBean("https://images.pexels.com/photos/262372/pexels-photo-262372.jpeg?w=940&h=650&auto=compress&cs=tinysrgb", "ly");
        MidPicBean midPicBean4 = new MidPicBean("https://images.pexels.com/photos/262372/pexels-photo-262372.jpeg?w=940&h=650&auto=compress&cs=tinysrgb", "ly");
        List<IBaseBean> list1 = new ArrayList<>();

        list1.add(midPicBean0);
        list1.add(midPicBean1);
        list1.add(midPicBean2);
        list1.add(midPicBean3);
        list1.add(midPicBean4);
        MorePicBean morePicBean = new MorePicBean();
        morePicBean.setPicBeanList(list1);
        list.add(morePicBean);
        final PagesPickerBean<IBaseBean> myWrappedPickerBean = new PagesPickerBean<>();
        ApiStore apiStore = RetrofitClient.getInstance().getRetrofit().create(ApiStore.class);
        Observable<HttpResponseBase<PagesPickerBean<RecommendBean>>> observer = apiStore.getRescommendList(page);
        return observer.compose(RxSchedulers.<HttpResponseBase<PagesPickerBean<RecommendBean>>>compose())
                .map(new BaseHttpmap<PagesPickerBean<RecommendBean>>())
                .flatMap(new Function<PagesPickerBean<RecommendBean>, ObservableSource<RecommendBean>>() {

                    @Override
                    public ObservableSource<RecommendBean> apply(PagesPickerBean<RecommendBean> pagesPickerBean) throws Exception {
                        myWrappedPickerBean.setNext(pagesPickerBean.isNext());
                        return Observable.fromIterable(pagesPickerBean.getData());
                    }
                })
                .collectInto(list, new BiConsumer<List<IBaseBean>, RecommendBean>() {
                    @Override
                    public void accept(List<IBaseBean> iBaseBeen, RecommendBean recommendBean) throws Exception {
                        recommendBean.setImage(Utils.AddBaseUrl(recommendBean.getImage()));
                        recommendBean.setAvatarPath(Utils.AddBaseUrl(recommendBean.getAvatarPath()));
                        iBaseBeen.add(recommendBean);
                    }
                })
                .map(new Function<List<IBaseBean>, PagesPickerBean<IBaseBean>>() {
                    @Override
                    public PagesPickerBean<IBaseBean> apply(List<IBaseBean> iBaseBeen) throws Exception {
                        myWrappedPickerBean.setData(iBaseBeen);
                        return myWrappedPickerBean;
                    }
                }).toObservable();
    }
}
