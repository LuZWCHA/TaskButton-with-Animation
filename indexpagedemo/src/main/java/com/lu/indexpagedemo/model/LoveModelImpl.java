package com.lu.indexpagedemo.model;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.LoveContract;

import io.reactivex.Observable;

/**
* Created by 陆正威 on 2017/04/15
*/

public class LoveModelImpl implements LoveContract.Model{

    @Override
    public Observable<IBaseBean> getLovefromNetWork() {
        return null;
    }

    @Override
    public Observable<IBaseBean> getLovefromLocation() {
        return null;
    }

    @Override
    public void uploadChange(int Loveid) {

    }
}