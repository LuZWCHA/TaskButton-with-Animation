package com.lu.welcomeloginpagedemos.mvp;


import android.support.annotation.NonNull;

import com.lu.welcomeloginpagedemos.mvp.Baseinterfaces.BasePresenter;
import com.lu.welcomeloginpagedemos.mvp.Baseinterfaces.BaseView;

import java.lang.ref.SoftReference;

/**
 * Created by 陆正威 on 2017/3/31.
 */

/********************************************************************************************
 *
 * 解决presenter因为持有View的引用而导致的内存泄露
 * 这里持有View的软引用并在下层（Activity层）调用和
 * 管理View的生命周期
 *
 ********************************************************************************************/
public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter {
    private SoftReference<T> mBaseView;
    private boolean hasConnected;

    public BasePresenterImpl(){
        hasConnected = false;
        mBaseView = null;
    }

    void attachView(@NonNull T t){
        mBaseView = new SoftReference<>(t);
        hasConnected = true;
    }

    protected T getView() {
        return mBaseView.get();
    }

    void detachView()
    {
        if(mBaseView != null){
            mBaseView.clear();
            mBaseView = null;
            hasConnected = false;
        }
    }

    public boolean isHasConnected(){return hasConnected;}

    public void start(){

    }
}
