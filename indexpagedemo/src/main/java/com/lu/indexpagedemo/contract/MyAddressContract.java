package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;
import com.lu.indexpagedemo.bean.AddressBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 陆正威 on 2017/4/14.
 */

public interface MyAddressContract {
    
public interface View extends BaseView{
    void initAddresses();
    void updateAddress(List<AddressBean> addressBeanList);
    void addAddress(AddressBean addressBean);
}

public interface Model extends BaseModel{
    Observable<List<AddressBean>> getAddressFromLocation();
    Observable<List<AddressBean>> getAddressFromNetWork();
    void deleteAddress(AddressBean addressBean);
}


}