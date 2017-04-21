package com.lu.indexpagedemo.base.rxjava.Notifys;

import com.lu.indexpagedemo.base.rxjava.Notify;

/**
 * Created by 陆正威 on 2017/4/16.
 */

public class BottomBarToggler extends Notify {
    private boolean show = false;
    public BottomBarToggler(boolean show){
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
