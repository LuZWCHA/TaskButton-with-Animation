package com.lu.indexpagedemo.view.adapters;

import com.chad.library.adapter.base.*;
import com.lu.indexpagedemo.bean.base.IBaseBean;

import java.util.List;

/**
 * Created by 陆正威 on 2017/4/15.
 */

public class MySimpleAdapter<T extends IBaseBean,V extends com.chad.library.adapter.base.BaseViewHolder> extends BaseQuickAdapter<T,V> {


    public MySimpleAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public MySimpleAdapter(List<T> data) {
        super(data);
    }

    public MySimpleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(V helper, T item) {

    }
}
