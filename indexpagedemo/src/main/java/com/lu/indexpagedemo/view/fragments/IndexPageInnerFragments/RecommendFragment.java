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
import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.base.rxjava.Notifys.MyNotifys;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.RecommendContract;
import com.lu.indexpagedemo.base.mvp.MvpBaseFragment;
import com.lu.indexpagedemo.presenter.RecommendPresenterImpl;
import com.lu.indexpagedemo.base.rxjava.RxBus;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.view.adapters.MyMultiAdapter;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RecommendFragment extends MvpBaseFragment<RecommendContract.View,RecommendPresenterImpl> implements RecommendContract.View,BaseQuickAdapter.OnItemChildClickListener,BaseQuickAdapter.OnItemClickListener {

    private final static String TAG = "RecommendFragment";
    private MyRecyclerView myRecyclerView;
    private MyMultiAdapter myMultiAdapter;
    private Disposable disposable;

    public RecommendFragment() {
    }

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
            myMultiAdapter.setOnItemChildClickListener(this);
            myMultiAdapter.setOnItemClickListener(this);
        }
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyNotifys.UpdateNotify event) {
        mPresenter.refreshData();
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
    protected RecommendPresenterImpl createPresent() {
        return new RecommendPresenterImpl();
    }

    @Override
    public void initList(PagesPickerBean<IBaseBean> pagesPickerBean) {
    }

    @Override
    public void updateList(PagesPickerBean<IBaseBean> pagesPickerBean) {
        myMultiAdapter.addData(pagesPickerBean.getData());
        Log.e("size",myMultiAdapter.getData().size()+","+myMultiAdapter.getPage());
    }

    @Override
    public void refreshList(PagesPickerBean<IBaseBean> pagesPickerBean) {
        myMultiAdapter.resetPage();
        myMultiAdapter.setNewData(pagesPickerBean.getData());
    }


    @Override
    public void loadMoreEnd() {
        myMultiAdapter.loadMoreEnd();
    }

    @Override
    public void loadMoreFaile() {
        myMultiAdapter.loadMoreFail();
    }

    @Override
    public void loadMoreComplete() {
        myMultiAdapter.loadMoreComplete();
        myMultiAdapter.addPage();
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if(view.getId() == R.id.innerbutton) Utils.MakeTost(false,"like at "+position);
        if(view.getId() == R.id.innerdrweeview) Utils.MakeTost(false,"pic at "+position);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Utils.MakeTost(false,"item at "+position);
    }
}
