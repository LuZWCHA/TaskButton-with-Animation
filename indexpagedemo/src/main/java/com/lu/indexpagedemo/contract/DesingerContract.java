package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;

import io.reactivex.Observable;

/**
 * Created by 陆正威 on 2017/4/6.
 */

public interface DesingerContract {
    
public interface View extends BaseView{

    void refreshData(PagesPickerBean<IBaseBean> pagesPickerBean);

    void updateList(PagesPickerBean<IBaseBean> desingerList);

    void loadMoreFaile();


}


public interface Model extends BaseModel{
    Observable<PagesPickerBean<IBaseBean>> getDesiners(int page);
}

}