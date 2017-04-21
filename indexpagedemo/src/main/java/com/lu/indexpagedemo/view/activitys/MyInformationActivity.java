package com.lu.indexpagedemo.view.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.mvp.MvpBaseActivity;
import com.lu.indexpagedemo.contract.MyImformationContract;
import com.lu.indexpagedemo.presenter.MyImformationPresenterImpl;

public class MyInformationActivity extends MvpBaseActivity<MyImformationContract.View, MyImformationPresenterImpl> implements MyImformationContract.View {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        if (savedInstanceState == null) {
            mToolbar = (Toolbar) findViewById(R.id.activity_my_information_toolbar);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    protected MyImformationPresenterImpl createPresent() {
        return new MyImformationPresenterImpl();
    }

}
