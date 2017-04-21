package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.contract.NowTieMethodContract;
import com.lu.indexpagedemo.model.NowTieMethodModelImpl;

/**
* Created by 陆正威 on 2017/04/20
*/

public class NowTieMethodPresenterImpl extends BasePresenterImpl<NowTieMethodContract.View>{
    NowTieMethodContract.Model model;

    public NowTieMethodPresenterImpl() {
        model = new NowTieMethodModelImpl();
    }
}