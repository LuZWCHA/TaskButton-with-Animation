package com.lu.indexpagedemo.bean;

import com.lu.indexpagedemo.bean.factories.RecyclerViewBeanFactory;
import com.lu.indexpagedemo.bean.base.IBaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陆正威 on 2017/4/4.
 */

public class MorePicBean implements IBaseBean {

    List<IBaseBean> picBeanList;

    public MorePicBean(){
        picBeanList = new ArrayList<>();
    }

    public List<IBaseBean> getPicBeanList(){
        return picBeanList;
    }

    public void setPicBeanList(List<IBaseBean> picBeanList) {
        this.picBeanList = picBeanList;
    }


    @Override
    public int type(RecyclerViewBeanFactory myRecyclerViewBeanFactory) {
        return myRecyclerViewBeanFactory.select(this);
    }

    @Override
    public long getUID() {
        return 0;
    }

}
