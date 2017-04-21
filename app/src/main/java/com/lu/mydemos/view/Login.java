package com.lu.mydemos.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lu.mydemos.R;
import com.lu.mydemos.contract.UserLoginContract;
import com.lu.mydemos.mvp.MvpBaseActivity;
import com.lu.mydemos.presenter.UserLoginPresenterImpl;
import com.lu.mytaskbutton.TaskButton;

import java.util.Timer;
import java.util.TimerTask;

public class Login extends MvpBaseActivity<UserLoginContract.View,UserLoginPresenterImpl> implements UserLoginContract.View, TaskButton.TaskButtonEventListener{

    TaskButton taskButton;
    BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_login);
        bottomBar = (BottomBar) findViewById(R.id.bottombar);


        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        taskButton = (TaskButton) findViewById(R.id.logintaskbutton);
        taskButton.setTaskButtonEventListener(this);
    }

    @Override
    protected UserLoginPresenterImpl createPresent() {
        return new UserLoginPresenterImpl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void OnTaskStart(View view) {
        Toast.makeText(Login.this, "start!", Toast.LENGTH_SHORT).show();
        mPresenter.login();
    }

    @Override
    public void OnTaskEnd(View view, int type) {
        Toast.makeText(Login.this,"end!",Toast.LENGTH_SHORT).show();
        if(type == TaskButton.NORMAL_STOP_ID){
            taskButton.setButtonColor(Color.argb(255,109,196,83));
            taskButton.setText("登录成功");
        }else {
            taskButton.setButtonColor(getResources().getColor(R.color.colorButtonPress));

            taskButton.setText("失 败");
        }
    }

    @Override
    public void setButtonColor(int buttonColor) {
        taskButton.setButtonColor(buttonColor);
    }

}
