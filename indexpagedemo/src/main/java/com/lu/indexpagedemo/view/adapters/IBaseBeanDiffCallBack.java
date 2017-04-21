package com.lu.indexpagedemo.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.lu.indexpagedemo.bean.base.IBaseBean;

import java.util.List;

/**
 * Created by 陆正威 on 2017/4/7.
 */

public class IBaseBeanDiffCallBack extends DiffUtil.Callback{
    List<IBaseBean>OldIBaseBean,NewIBaseBean;

    public IBaseBeanDiffCallBack(@NonNull List<IBaseBean>oldIBaseBean,@NonNull List<IBaseBean>newIBaseBean) {
        OldIBaseBean = oldIBaseBean;
        NewIBaseBean = newIBaseBean;
    }

    @Override
    public int getOldListSize() {
        return OldIBaseBean.size();
    }

    @Override
    public int getNewListSize() {
        return NewIBaseBean.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return OldIBaseBean.get(oldItemPosition).getUID() == NewIBaseBean.get(newItemPosition).getUID();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
