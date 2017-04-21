package com.lu.indexpagedemo.bean;

import com.lu.indexpagedemo.bean.factories.RecyclerViewBeanFactory;
import com.lu.indexpagedemo.bean.base.IBaseBean;

/**
 * Created by 陆正威 on 2017/4/8.
 */

public class MidPicBean implements IBaseBean {

    private String imageurl;
    private String designername;

    public MidPicBean(String imageurl, String designername) {
        this.imageurl = imageurl;
        this.designername = designername;
    }

    @Override
    public int type(RecyclerViewBeanFactory myRecyclerViewBeanFactory) {
        return myRecyclerViewBeanFactory.select(this);
    }

    @Override
    public long getUID() {
        return 0;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDesignername() {
        return designername;
    }

    public void setDesignername(String designername) {
        this.designername = designername;
    }

}
