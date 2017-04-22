package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.contract.CustomContract;
import com.lu.indexpagedemo.contract.CustomSelectContract;
import com.lu.indexpagedemo.model.CustomSelectModelImpl;

/**
* Created by 陆正威 on 2017/04/21
*/

public class CustomSelectPresenterImpl extends BasePresenterImpl<CustomSelectContract.View>{
    CustomSelectContract.Model model;

    public CustomSelectPresenterImpl() {
        model = new CustomSelectModelImpl();
    }
}