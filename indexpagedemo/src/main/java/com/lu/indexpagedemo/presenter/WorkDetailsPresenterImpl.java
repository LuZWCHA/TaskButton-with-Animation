package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.contract.WorkDetailsContract;
import com.lu.indexpagedemo.model.WorkDetailsModelImpl;

/**
* Created by 陆正威 on 2017/04/21
*/

public class WorkDetailsPresenterImpl extends BasePresenterImpl<WorkDetailsContract.View>{
    WorkDetailsContract.Model model;

    public WorkDetailsPresenterImpl() {
        model = new WorkDetailsModelImpl();
    }
}