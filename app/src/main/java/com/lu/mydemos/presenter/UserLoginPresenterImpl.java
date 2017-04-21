package com.lu.mydemos.presenter;
import android.graphics.Color;

import com.lu.mydemos.contract.UserLoginContract;
import com.lu.mydemos.mvp.BasePresenterImpl;
import com.lu.mydemos.view.Login;

import java.util.Timer;
import java.util.TimerTask;

/**
* Created by 陆正威 on 2017/03/31
*/

public class UserLoginPresenterImpl extends BasePresenterImpl<UserLoginContract.View> implements UserLoginContract.Presenter{

    public UserLoginPresenterImpl() {
        super();
    }

    @Override
    public void start() {

    }

    @Override
    public void login() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //taskButton.forcestopTaskAnimation();
                getView().setButtonColor(Color.DKGRAY);
                this.cancel();
            }
        }, 1300);
    }
}