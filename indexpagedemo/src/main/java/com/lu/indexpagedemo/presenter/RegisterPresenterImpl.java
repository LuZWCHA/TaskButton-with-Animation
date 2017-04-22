package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.contract.RegisterContract;
import com.lu.indexpagedemo.model.RegisterModelImpl;

/**
* Created by 陆正威 on 2017/04/22
*/

public class RegisterPresenterImpl extends BasePresenterImpl<RegisterContract.View>{
    RegisterContract.Model model;
    public RegisterPresenterImpl(){
        model = new RegisterModelImpl();
    }

}