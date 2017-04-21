package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.bean.AddressBean;
import com.lu.indexpagedemo.contract.MyAddressEditionContract;
import com.lu.indexpagedemo.model.MyAddressEditionModelImpl;

/**
* Created by 陆正威 on 2017/04/14
*/

public class MyAddressEditionPresenterImpl extends BasePresenterImpl<MyAddressEditionContract.View>{

    public MyAddressEditionContract.Model model;
    public MyAddressEditionPresenterImpl(){
        model = new MyAddressEditionModelImpl();
    }

    public void saveNewAddress(AddressBean addressBean){
        model.saveLocation(addressBean);
    }
}