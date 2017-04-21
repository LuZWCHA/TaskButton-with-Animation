package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;
import com.lu.indexpagedemo.bean.AddressBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 陆正威 on 2017/4/14.
 */

public interface MyAddressEditionContract {
    
public interface View extends BaseView{
    void uploadNewAddress();
    void showSuccess();
    void showFail();
}

public interface Model extends BaseModel{
    void uploadNewAddress(AddressBean addressBean);
    void saveLocation(AddressBean addressBean);
    Observable<AddressBean> getAddressBean();
    Observable<List<AddressBean>> getAddressfromNetWork();
}


}