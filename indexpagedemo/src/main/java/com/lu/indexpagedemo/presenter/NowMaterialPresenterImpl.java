package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.contract.NowMaterialContract;
import com.lu.indexpagedemo.model.NowMaterialModelImpl;

/**
* Created by 陆正威 on 2017/04/20
*/

public class NowMaterialPresenterImpl extends BasePresenterImpl<NowMaterialContract.View>{

    NowMaterialContract.Model model;
    public NowMaterialPresenterImpl() {
        model = new NowMaterialModelImpl();
    }
}