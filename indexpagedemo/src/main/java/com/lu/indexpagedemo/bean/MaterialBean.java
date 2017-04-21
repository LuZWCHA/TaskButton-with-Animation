package com.lu.indexpagedemo.bean;

import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.bean.factories.RecyclerViewBeanFactory;

/**
 * Created by 陆正威 on 2017/4/20.
 */

public class MaterialBean implements IBaseBean {

    private String imageUrl;

    public MaterialBean(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int type(RecyclerViewBeanFactory myRecyclerViewBeanFactory) {
        return 0;
    }

    @Override
    public long getUID() {
        return 0;
    }
}
