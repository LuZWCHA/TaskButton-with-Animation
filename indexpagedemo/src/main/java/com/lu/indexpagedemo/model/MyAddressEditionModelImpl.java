package com.lu.indexpagedemo.model;
import com.com.sky.downloader.greendao.AddressBeanDao;
import com.com.sky.downloader.greendao.DBManager;
import com.lu.indexpagedemo.bean.AddressBean;
import com.lu.indexpagedemo.contract.MyAddressEditionContract;

import java.util.List;

import io.reactivex.Observable;

/**
* Created by 陆正威 on 2017/04/14
*/

public class MyAddressEditionModelImpl implements MyAddressEditionContract.Model{


    @Override
    public void uploadNewAddress(AddressBean addressBean) {

    }

    @Override
    public void saveLocation(AddressBean addressBean) {
        AddressBeanDao addressBeanDao = DBManager.getInstance().getDaoSession().getAddressBeanDao();
        addressBeanDao.insertOrReplace(addressBean);
    }

    @Override
    public Observable<AddressBean> getAddressBean() {
        return null;
    }

    @Override
    public Observable<List<AddressBean>> getAddressfromNetWork() {
        return null;
    }
}