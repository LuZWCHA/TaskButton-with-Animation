package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.contract.NowMatchContract;
import com.lu.indexpagedemo.contract.NowMaterialContract;
import com.lu.indexpagedemo.model.NowMatchModelImpl;

/**
* Created by 陆正威 on 2017/04/20
*/

public class NowMatchPresenterImpl extends BasePresenterImpl<NowMaterialContract.View>{

    NowMatchContract.Model model;
    public NowMatchPresenterImpl(){
        model = new NowMatchModelImpl();
    }

}