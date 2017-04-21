package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;
import com.lu.indexpagedemo.bean.base.IBaseBean;

import io.reactivex.Observable;

/**
 * Created by 陆正威 on 2017/4/15.
 */

public interface LoveContract {
    
public interface View extends BaseView{
    void updateList();
    void uploadChange();
    void hideRefresh();
}

public interface Model extends BaseModel{
    Observable<IBaseBean> getLovefromNetWork();
    Observable<IBaseBean> getLovefromLocation();
    void uploadChange(int Loveid);
}


}