package com.lu.indexpagedemo.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lu.indexpagedemo.bean.base.IBaseBean;

/**
 * Created by 陆正威 on 2017/4/4.
 */

public class  BaseViewHolder<T extends IBaseBean> extends com.chad.library.adapter.base.BaseViewHolder {

    public BaseViewHolder(View view) {
        super(view);
    }

    public BaseViewHolder(View view,BaseQuickAdapter.OnItemChildClickListener listener){super(view);}

    public BaseViewHolder(View view,BaseQuickAdapter.OnItemClickListener listener){super(view);}

    public void setUpViews(@NonNull final T baseBean){};
}
