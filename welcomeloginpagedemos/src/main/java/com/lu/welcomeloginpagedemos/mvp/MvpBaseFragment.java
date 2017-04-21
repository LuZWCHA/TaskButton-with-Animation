package com.lu.welcomeloginpagedemos.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lu.welcomeloginpagedemos.mvp.Baseinterfaces.BaseView;

/**
 * Created by 陆正威 on 2017/4/3.
 */

public abstract class MvpBaseFragment<V extends BaseView,P extends BasePresenterImpl> extends Fragment{
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresent();
        mPresenter.attachView((V)this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter.start();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detachView();
    }

    abstract P createPresent();
}
