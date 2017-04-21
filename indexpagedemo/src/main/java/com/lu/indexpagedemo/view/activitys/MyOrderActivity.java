package com.lu.indexpagedemo.view.activitys;

import android.os.Bundle;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.contract.OrderContract;
import com.lu.indexpagedemo.base.mvp.MvpBaseActivity;
import com.lu.indexpagedemo.presenter.OrderPresenterImpl;

public class MyOrderActivity extends MvpBaseActivity<OrderContract.View,OrderPresenterImpl>implements OrderContract.View{

    // TODO: 2017/4/11 从UserCenterFragment跳转而来
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_item_cart);
    }

    @Override
    protected OrderPresenterImpl createPresent() {
        return new OrderPresenterImpl();
    }
}
