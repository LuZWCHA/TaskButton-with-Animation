package com.lu.indexpagedemo.model;

import com.lu.indexpagedemo.Api.ApiStore;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.DesignerBean;
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
import java.util.List;

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
        Observable<HttpResponseBase<PagesPickerBean<DesignerBean>>> observer = apiStore.getDesignerList(page);
        return observer.compose(RxSchedulers.<HttpResponseBase<PagesPickerBean<DesignerBean>>>compose())
                .map(new BaseHttpmap<PagesPickerBean<DesignerBean>>())
                .flatMap(new Function<PagesPickerBean<DesignerBean>, ObservableSource<DesignerBean>>() {

                    @Override
                    public ObservableSource<DesignerBean> apply(PagesPickerBean<DesignerBean> pagesPickerBean) throws Exception {
                        myWrappedPickerBean.setNext(pagesPickerBean.isNext());
                        return Observable.fromIterable(pagesPickerBean.getData());
                    }
                })
                .collectInto(list, new BiConsumer<List<IBaseBean>, DesignerBean>() {
                    @Override
                    public void accept(List<IBaseBean> iBaseBeen, DesignerBean designerBean) throws Exception {
                        designerBean.setImage(Utils.AddBaseUrl(designerBean.getImage()));
                        designerBean.setAvatarPath(Utils.AddBaseUrl(designerBean.getAvatarPath()));
                        iBaseBeen.add(designerBean);
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