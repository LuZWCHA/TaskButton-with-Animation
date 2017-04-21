package com.lu.indexpagedemo.view.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.base.mvp.MvpBaseActivity;
import com.lu.indexpagedemo.bean.WorkBean;
import com.lu.indexpagedemo.contract.LoveContract;
import com.lu.indexpagedemo.presenter.LovePresenterImpl;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;
import com.lu.indexpagedemo.view.adapters.MySimpleAdapter;
import com.lu.indexpagedemo.view.viewholders.LoveViewHolder;

import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class LoveActivity extends MvpBaseActivity<LoveContract.View,LovePresenterImpl>implements LoveContract.View {

    private MyRecyclerView myRecyclerView;
    private LoveAdapter mLoveAdapter;
    private PtrFrameLayout mPtrFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);

        if(savedInstanceState == null){
            mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.love_ptrframelayout);
            myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            myRecyclerView = (MyRecyclerView) findViewById(R.id.love_recyclerview);

            // TODO: 2017/4/15 初始化Adapter
            //mLoveAdapter = new LoveAdapter();
            ptrFrameLayoutInitiz();
        }
    }

    void ptrFrameLayoutInitiz(){
        final StoreHouseHeader header = new StoreHouseHeader(AppManager.app());
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        header.initWithString("LOADING");
        header.setScale(2);
        header.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        header.setLineWidth(4);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
    }

    @Override
    protected LovePresenterImpl createPresent() {
        return new LovePresenterImpl();
    }

    @Override
    public void updateList() {

    }

    @Override
    public void uploadChange() {

    }

    @Override
    public void hideRefresh() {

    }
}

class LoveAdapter extends MySimpleAdapter<WorkBean,LoveViewHolder> {
    public LoveAdapter(int layoutResId, List<WorkBean> data) {
        super(layoutResId, data);
    }

    public LoveAdapter(List<WorkBean> data) {
        super(data);
    }

    public LoveAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(LoveViewHolder helper, WorkBean item) {
        //// TODO: 2017/4/15 等待作品Bean数据结构
    }
}
