package com.lu.indexpagedemo.view.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.bean.MorePicBean;
import com.lu.indexpagedemo.view.adapters.BaseViewHolder;
import com.lu.indexpagedemo.view.adapters.MyMultiAdapter;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;

import java.util.ArrayList;

/**
 * Created by 陆正威 on 2017/4/4.
 */

public class MoreImageViewHolder extends BaseViewHolder<MorePicBean>{

    private MyMultiAdapter myMultiAdapter;

    public MoreImageViewHolder(View itemView, BaseQuickAdapter.OnItemChildClickListener listener) {
        super(itemView);

        myMultiAdapter = new MyMultiAdapter(new ArrayList<IBaseBean>());
        MyRecyclerView myRecyclerView = getView(R.id.innerrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AppManager.app(),LinearLayoutManager.HORIZONTAL,false);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setNestedScrollingEnabled(false);
        myMultiAdapter.bindToRecyclerView(myRecyclerView);
        myRecyclerView.setAdapter(myMultiAdapter);
        myMultiAdapter.setEmptyView(R.layout.recyclerview_emptyview2);
        myMultiAdapter.setOnItemChildClickListener(listener);
    }


    @Override
    public void setUpViews(@NonNull MorePicBean baseBean) {
        myMultiAdapter.setNewData(baseBean.getPicBeanList());
    }


}
