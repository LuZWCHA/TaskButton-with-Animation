package com.lu.indexpagedemo.bean.factories;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.view.adapters.BaseRecyclerViewAdapter;
import com.lu.indexpagedemo.view.adapters.BaseViewHolder;

/**
 * Created by 陆正威 on 2017/4/4.
 */

public interface IViewHolderFactory {
    BaseViewHolder createViewHolder(int type, View itemview, BaseQuickAdapter.OnItemChildClickListener childlistener,BaseQuickAdapter.OnItemClickListener listener);
}
