package com.lu.welcomeloginpagedemos.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lu.welcomeloginpagedemos.R;
import com.lu.welcomeloginpagedemos.contract.UserLoginContract;
import com.lu.welcomeloginpagedemos.mvp.BasePresenterImpl;
import com.lu.welcomeloginpagedemos.mvp.MvpBaseActivity;
import com.lu.welcomeloginpagedemos.presenter.UserLoginPresenterImpl;

public class LoginActivity extends MvpBaseActivity<UserLoginContract.View,UserLoginPresenterImpl> implements UserLoginContract.View,View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected UserLoginPresenterImpl createPresent() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassWord() {
        return null;
    }
}
