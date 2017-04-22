package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.contract.LoginContract;
import com.lu.indexpagedemo.model.LoginModelImpl;

/**
* Created by 陆正威 on 2017/04/22
*/

public class LoginPresenterImpl extends BasePresenterImpl<LoginContract.View>{
    LoginContract.Model model;
    public LoginPresenterImpl(){
        model = new LoginModelImpl();
    }

}