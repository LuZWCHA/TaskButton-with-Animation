package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.base.rxjava.RxSchedulers;
import com.lu.indexpagedemo.bean.AddressBean;
import com.lu.indexpagedemo.contract.MyAddressContract;
import com.lu.indexpagedemo.contract.MyAddressEditionContract;
import com.lu.indexpagedemo.model.MyAddressEditionModelImpl;
import com.lu.indexpagedemo.model.MyAddressModelImpl;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
* Created by 陆正威 on 2017/04/14
*/

public class MyAddressPresenterImpl extends BasePresenterImpl<MyAddressContract.View> {

    private MyAddressContract.Model model;

    public MyAddressPresenterImpl(){
        model = new MyAddressModelImpl();
    }

    public void deleteAddress(AddressBean addressBean){
        model.deleteAddress(addressBean);
    }

    public void initAddressList(){
        model.getAddressFromLocation().compose(RxSchedulers.<List<AddressBean>>compose())
        .subscribe(new Consumer<List<AddressBean>>() {
            @Override
            public void accept(List<AddressBean> addressBeanList) throws Exception {
                getView().updateAddress(addressBeanList);
            }
        });
    }

}