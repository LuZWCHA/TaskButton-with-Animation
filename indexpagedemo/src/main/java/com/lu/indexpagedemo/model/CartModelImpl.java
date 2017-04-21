package com.lu.indexpagedemo.model;
import com.lu.indexpagedemo.bean.WorkBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.CartContract;

import java.util.List;

import io.reactivex.Observable;

/**
* Created by 陆正威 on 2017/04/15
*/

public class CartModelImpl implements CartContract.Model{


    @Override
    public Observable<List<WorkBean>> getCartfromNetWork() {
        return null;
    }

    @Override
    public Observable<List<WorkBean>> getCartfromLocation() {
        return null;
    }

    @Override
    public void updateChange(int cartid) {

    }
}