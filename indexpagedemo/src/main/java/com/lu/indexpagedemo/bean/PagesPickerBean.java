package com.lu.indexpagedemo.bean;

import com.google.gson.annotations.SerializedName;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.bean.factories.RecyclerViewBeanFactory;

import java.util.List;

/**
 * Created by 陆正威 on 2017/4/8.
 */

public class PagesPickerBean<T extends IBaseBean> {
    @SerializedName("next")
    private boolean next;
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }
}
