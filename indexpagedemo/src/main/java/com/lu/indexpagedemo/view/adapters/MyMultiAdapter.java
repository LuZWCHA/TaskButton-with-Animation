package com.lu.indexpagedemo.view.adapters;

import android.support.v7.util.DiffUtil;
import android.view.ViewGroup;

import com.chad.library.adapter.base.*;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.bean.factories.RecyclerViewBeanFactory;
import com.lu.indexpagedemo.base.rxjava.RxSchedulers;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by 陆正威 on 2017/4/9.
 */

public class MyMultiAdapter extends BaseQuickAdapter<IBaseBean,com.lu.indexpagedemo.view.adapters.BaseViewHolder>{
    private RecyclerViewBeanFactory recyclerViewBeanFactory = new RecyclerViewBeanFactory();

    private int page = 1;

    public void setRecyclerViewBeanFactory(RecyclerViewBeanFactory recyclerViewBeanFactory){
        this.recyclerViewBeanFactory = recyclerViewBeanFactory;
    }

    public int getPage() {
        return page;
    }

    public void resetPage() {
        this.page = 1;
    }

    public void addPage(){
        page ++;
    }

    public MyMultiAdapter(List<IBaseBean> data) {
        super(data);
    }

    public MyMultiAdapter(int layoutResId) {
        super(layoutResId);
    }

    public MyMultiAdapter(int layoutResId, List<IBaseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected int getDefItemViewType(int position) {
        return getData().get(position).type(recyclerViewBeanFactory);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return recyclerViewBeanFactory.createViewHolder(viewType,getItemView(viewType,parent),getOnItemChildClickListener(),getOnItemClickListener());
    }


    @Override
    protected void convert(com.lu.indexpagedemo.view.adapters.BaseViewHolder helper, IBaseBean item) {
        helper.setUpViews(item);
    }

    public Disposable setNewDatabyDiff(List<IBaseBean> Data){
        return Observable.just(new IBaseBeanDiffCallBack(getData(),Data))
                .compose(RxSchedulers.<IBaseBeanDiffCallBack>compose())
                .map(new Function<IBaseBeanDiffCallBack, DiffUtil.DiffResult>() {
                    @Override
                    public DiffUtil.DiffResult apply(IBaseBeanDiffCallBack iBaseBeanDiffCallBack) throws Exception {
                        return DiffUtil.calculateDiff(iBaseBeanDiffCallBack);
                    }
                })
                .subscribeWith(new DisposableObserver<DiffUtil.DiffResult>() {

                    @Override
                    public void onNext(DiffUtil.DiffResult value) {
                        value.dispatchUpdatesTo(MyMultiAdapter.this);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
