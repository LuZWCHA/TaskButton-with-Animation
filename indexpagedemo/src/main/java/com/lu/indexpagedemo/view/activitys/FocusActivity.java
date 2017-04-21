package com.lu.indexpagedemo.view.activitys;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.MvpBaseActivity;
import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.contract.FocusContract;
import com.lu.indexpagedemo.presenter.FocusPresenterImpl;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;
import com.lu.indexpagedemo.view.adapters.MySimpleAdapter;
import com.lu.indexpagedemo.view.viewholders.FocusViewHolder;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class FocusActivity extends MvpBaseActivity<FocusContract.View,FocusPresenterImpl>implements FocusContract.View {


    private MyRecyclerView myRecyclerView;
    private FocusAdapter mFocusAdapter;
    private PtrFrameLayout mPtrFrameLayout;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        if(savedInstanceState == null){

            toolBarInitiz();
            recyclerviewInitiz();
            ptrFrameLayoutInitiz();
        }
    }

    void toolBarInitiz(){
        mToolBar = (Toolbar) findViewById(R.id.focus_toolbar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                mPresenter.updateList();
            }
        });
    }

    void recyclerviewInitiz(){
        myRecyclerView = (MyRecyclerView) findViewById(R.id.focus_recyclerview);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.focus_ptrframelayout);
        mFocusAdapter = new FocusAdapter(R.layout.recyclerview_item_focus,null);
        mFocusAdapter.bindToRecyclerView(myRecyclerView);
        mFocusAdapter.setEmptyView(R.layout.recylcerview_emptyview);
        mFocusAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.focus_item)
                    Utils.MakeTost(false,"点击了item");
                else
                    Utils.MakeTost(false,"点击了取消关注");
            }
        });
    }

    @Override
    protected FocusPresenterImpl createPresent() {
        return new FocusPresenterImpl();
    }

    @Override
    public void updateList(List<DesignerBean> designerBeans) {
        mFocusAdapter.setNewData(designerBeans);
    }

    @Override
    public void uploadChange() {

    }

    @Override
    public void hideRefresh() {
        mPtrFrameLayout.refreshComplete();

    }
}


class FocusAdapter extends MySimpleAdapter<DesignerBean,FocusViewHolder> {

    public FocusAdapter(List<DesignerBean> data) {
        super(data);
    }

    FocusAdapter(int layoutResId, List<DesignerBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(FocusViewHolder helper, DesignerBean item) {
        helper.setText(R.id.focus_item_name,item.getNickname());
        Utils.load(item.getAvatarPath(),(SimpleDraweeView)helper.getView(R.id.focus_item_header),Utils.dip2px(64),Utils.dip2px(64));
    }
}