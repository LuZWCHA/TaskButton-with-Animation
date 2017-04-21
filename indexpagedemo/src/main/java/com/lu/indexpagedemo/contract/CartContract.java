package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;
import com.lu.indexpagedemo.bean.WorkBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 陆正威 on 2017/4/15.
 */

public interface CartContract {
    
public interface View extends BaseView{
    void updateList(List<WorkBean> workBeanList);
    void updateSum();
}

public interface Model extends BaseModel{
    Observable<List<WorkBean>> getCartfromNetWork();
    Observable<List<WorkBean>> getCartfromLocation();
    void updateChange(int cartid);
}

}