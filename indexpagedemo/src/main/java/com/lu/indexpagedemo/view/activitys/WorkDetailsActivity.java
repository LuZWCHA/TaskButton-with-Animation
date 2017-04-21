package com.lu.indexpagedemo.view.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.mvp.MvpBaseActivity;
import com.lu.indexpagedemo.contract.WorkDetailsContract;
import com.lu.indexpagedemo.model.WorkDetailsModelImpl;
import com.lu.indexpagedemo.presenter.WorkDetailsPresenterImpl;

public class WorkDetailsActivity extends MvpBaseActivity<WorkDetailsContract.View,WorkDetailsPresenterImpl>implements WorkDetailsContract.View {

    public final static String TAG = "WorkDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_details);
    }

    @Override
    protected WorkDetailsPresenterImpl createPresent() {
        return new WorkDetailsPresenterImpl();
    }

    @Override
    public String getTAG() {
        return TAG;
    }
}