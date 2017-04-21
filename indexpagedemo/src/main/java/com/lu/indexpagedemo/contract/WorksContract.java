package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;

import io.reactivex.Observable;

/**
 * Created by 陆正威 on 2017/4/10.
 */

public interface WorksContract {
    interface View extends BaseView{
        void updateList(PagesPickerBean<IBaseBean> worksList);
        void refreData(PagesPickerBean<IBaseBean> worksList);
    }

    interface Model extends BaseModel{
    Observable<PagesPickerBean<IBaseBean>> getWorks(int page);
}


}