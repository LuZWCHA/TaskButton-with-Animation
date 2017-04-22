package com.lu.indexpagedemo.view.fragments.IndexPageInnerFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.DesingerContract;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.presenter.DesingerPresenterImpl;
import com.lu.indexpagedemo.base.rxjava.Notifys.MyNotifys;
import com.lu.indexpagedemo.base.rxjava.RxBus;
import com.lu.indexpagedemo.view.adapters.MyMultiAdapter;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by 陆正威 on 2017/4/6.
 */

public class DesignerFragment extends MvpBaseFragment<DesingerContract.View,DesingerPresenterImpl>implements DesingerContract.View {

    private final static String TAG = "DesignerFragment";
    private MyRecyclerView myRecyclerView;
    private MyMultiAdapter myMultiAdapter;

    public DesignerFragment(){

    }

    public static DesignerFragment newInstance(){
        return new DesignerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        if(rootView  == null) {
            rootView = inflater.inflate(R.layout.fragment_designer,container, false);
            myRecyclerView = (MyRecyclerView) rootView.findViewById(R.id.designerlist);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AppManager.app());
            myRecyclerView.setLayoutManager(linearLayoutManager);
            myMultiAdapter = new MyMultiAdapter(R.layout.recyclerview_item_bpst2,new ArrayList<IBaseBean>());
            myMultiAdapter.bindToRecyclerView(myRecyclerView);
            myMultiAdapter.disableLoadMoreIfNotFullPage();
            myMultiAdapter.setLoadMoreView(new SimpleLoadMoreView());
            myMultiAdapter.setEmptyView(R.layout.recylcerview_emptyview);
            myMultiAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    //if(!myMultiAdapter.isLoading())
                        mPresenter.updateData(myMultiAdapter.getPage());
                }
            },myRecyclerView);
            myRecyclerView.setAdapter(myMultiAdapter);
        }
        return rootView;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyNotifys.UpdateNotify event) {
        mPresenter.refresh();
    }

    @Override
    public void refreshData(PagesPickerBean<IBaseBean> pagesPickerBean){
        myMultiAdapter.resetPage();
        myMultiAdapter.setNewData(pagesPickerBean.getData());
    }

    @Override
    protected void onFragmentVisible(boolean isFirstVisible) {
        if(isFirstVisible) if(isFirstVisible)
            mPresenter.updateData(1);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onFragmentInVisible() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected DesingerPresenterImpl createPresent() {
        return new DesingerPresenterImpl();
    }

    @Override
    public void updateList(PagesPickerBean<IBaseBean> desingerList) {
        myMultiAdapter.addData(desingerList.getData());
        myMultiAdapter.addPage();
        if(!desingerList.isNext()){
            myMultiAdapter.loadMoreEnd();
        }else {
            myMultiAdapter.loadMoreComplete();
        }
        //Log.e("size",myMultiAdapter.getData().size()+","+myMultiAdapter.getPage());
    }

    @Override
    public void loadMoreFaile() {
        myMultiAdapter.loadMoreFail();
    }

    @Override
    public String getTAG() {
        return TAG;
    }
}
