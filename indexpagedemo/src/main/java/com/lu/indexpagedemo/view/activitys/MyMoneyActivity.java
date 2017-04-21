package com.lu.indexpagedemo.view.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.mvp.MvpBaseActivity;
import com.lu.indexpagedemo.contract.MyMoneyContract;
import com.lu.indexpagedemo.presenter.MyMoneyPresenterImpl;

public class MyMoneyActivity extends MvpBaseActivity<MyMoneyContract.View, MyMoneyPresenterImpl> implements MyMoneyContract.View, View.OnClickListener {


    private Toolbar mMymoneyToolbar;
    private LinearLayout mChargemoney;
    private LinearLayout mMycard;
    private LinearLayout mRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_money);

        if (savedInstanceState == null) {
            initView();
        }
    }

    @Override
    protected MyMoneyPresenterImpl createPresent() {
        return new MyMoneyPresenterImpl();
    }

    private void initView() {
        mMymoneyToolbar = (Toolbar) findViewById(R.id.mymoney_toolbar);
        mChargemoney = (LinearLayout) findViewById(R.id.chargemoney);
        mChargemoney.setOnClickListener(this);
        mMycard = (LinearLayout) findViewById(R.id.mycard);
        mMycard.setOnClickListener(this);
        mRecord = (LinearLayout) findViewById(R.id.record);
        mRecord.setOnClickListener(this);
        mMymoneyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chargemoney:
                break;
            case R.id.mycard:
                break;
            case R.id.record:
                break;
        }
    }
    private void goReCord() {

    }

    private void goCard() {

    }

    private void goReCharge() {

    }

}
