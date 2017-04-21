package com.lu.indexpagedemo.bean;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by 陆正威 on 2017/4/17.
 */
//@Entity
public class CartItemBean {
    @Id
    private Long cartItemId;

    @ToOne
    private WorkBean workBean;
    @NotNull
    private int num;
    private double backdata;//备用

    public WorkBean getWorkBean() {
        return workBean;
    }

    public void setWorkBean(WorkBean workBean) {
        this.workBean = workBean;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getBackdata() {
        return backdata;
    }

    public void setBackdata(double backdata) {
        this.backdata = backdata;
    }

    public CartItemBean(Long cartItemId, WorkBean workBean, int num) {
        this.cartItemId = cartItemId;
        this.workBean = workBean;
        this.num = num;
    }
}
