package com.lu.indexpagedemo.view.fragments.IndexPageInnerFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.WorksContract;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.presenter.WorksPresenterImpl;
import com.lu.indexpagedemo.base.rxjava.Notifys.MyNotifys;
import com.lu.indexpagedemo.base.rxjava.RxBus;
import com.lu.indexpagedemo.base.rxjava.RxSchedulers;
import com.lu.indexpagedemo.view.adapters.MyMultiAdapter;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class WorksFragment extends MvpBaseFragment<WorksContract.View,WorksPresenterImpl> implements WorksContract.View {

    private final static String TAG = "WorkFragment";
    private MyRecyclerView myRecyclerView;
    private MyMultiAdapter myMultiAdapter;

    public WorksFragment() {}

    public static WorksFragment newInstance() {
        return  new WorksFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        Log.e(TAG,"onCreateView");
        if(rootView  == null) {
            rootView = inflater.inflate(R.layout.fragment_recommend, container, false);
            myRecyclerView = (MyRecyclerView) rootView.findViewById(R.id.recommendlist);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AppManager.app());
            myRecyclerView.setLayoutManager(linearLayoutManager);
            myMultiAdapter = new MyMultiAdapter(R.layout.recyclerview_item_bpst,new ArrayList<IBaseBean>());
            myMultiAdapter.bindToRecyclerView(myRecyclerView);
            myRecyclerView.setAdapter(myMultiAdapter);
            myMultiAdapter.disableLoadMoreIfNotFullPage();
            myMultiAdapter.setLoadMoreView(new SimpleLoadMoreView());
            myMultiAdapter.setEmptyView(R.layout.recylcerview_emptyview);

            myMultiAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    mPresenter.updateData(myMultiAdapter.getPage());
                }
            },myRecyclerView);

        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"onAttach");

    }

    @Override
    protected WorksPresenterImpl createPresent() {
        return new WorksPresenterImpl();
    }

    @Override
    public void updateList(PagesPickerBean<IBaseBean> worksList) {
        myMultiAdapter.addData(worksList.getData());
        myMultiAdapter.addPage();

        if(!worksList.isNext())
            myMultiAdapter.loadMoreEnd();
        else
            myMultiAdapter.loadMoreComplete();
    }

    @Override
    public void referData(PagesPickerBean<IBaseBean> worksList) {
        myMultiAdapter.resetPage();
        myMultiAdapter.setNewData(worksList.getData());
    }

    @Override
    public void loadMoreFail() {
        myMultiAdapter.loadMoreFail();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyNotifys.UpdateNotify event) {
        myMultiAdapter.setNewData(null);
        mPresenter.updateData(1);
    }

    @Override
    protected void onFragmentVisible(boolean isFirstVisible) {
        if(isFirstVisible)
            mPresenter.updateData(1);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onFragmentInVisible() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public String getTAG() {
        return TAG;
    }
}
