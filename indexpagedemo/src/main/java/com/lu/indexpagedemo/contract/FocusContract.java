package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;
import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 陆正威 on 2017/4/15.
 */

public interface FocusContract {
    
public interface View extends BaseView{
    void updateList(List<DesignerBean> designerBeans);
    void uploadChange();
    void hideRefresh();
}

public interface Model extends BaseModel{
    Observable<List<DesignerBean>> getFocusfromNetWork();
    Observable<List<DesignerBean>> getFocusfromLocation();
    void uploadChange(int Fouceid);
}


}