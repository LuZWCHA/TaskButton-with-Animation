package com.lu.indexpagedemo.model;
import com.com.sky.downloader.greendao.DBManager;
import com.lu.indexpagedemo.bean.AddressBean;
import com.lu.indexpagedemo.contract.MyAddressContract;

import java.util.List;

import io.reactivex.Observable;

/**
* Created by 陆正威 on 2017/04/14
*/

public class MyAddressModelImpl implements MyAddressContract.Model{

    @Override
    public Observable<List<AddressBean>> getAddressFromLocation() {
        return Observable.just(DBManager.getInstance().getDaoSession().getAddressBeanDao().loadAll());
    }

    @Override
    public Observable<List<AddressBean>> getAddressFromNetWork() {
        return null;
    }
    @Override
    public void deleteAddress(AddressBean addressBean){
        DBManager.getInstance().getDaoSession().getAddressBeanDao().delete(addressBean);
    }
}