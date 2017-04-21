package com.lu.indexpagedemo.bean.base;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lu.indexpagedemo.bean.factories.RecyclerViewBeanFactory;

import java.io.Serializable;

/**
 * Created by 陆正威 on 2017/4/3.
 */

public interface IBaseBean extends Serializable {
    int type(RecyclerViewBeanFactory myRecyclerViewBeanFactory);
    long getUID();//返回能标记实例的唯一值
}
