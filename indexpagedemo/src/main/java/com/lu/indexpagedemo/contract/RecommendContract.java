package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.bean.PagesPickerBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;

import io.reactivex.Observable;

/**
 * Created by 陆正威 on 2017/4/6.
 */

public interface RecommendContract {
    interface View extends BaseView{
        // TODO: 2017/4/12 初始化信息从数据库
        void initList(PagesPickerBean<IBaseBean> pagesPickerBean);
        void updateList(PagesPickerBean<IBaseBean> pagesPickerBean);
        void refreshList(PagesPickerBean<IBaseBean> pagesPickerBean);
        void loadMoreFaile();
    }

    interface Model extends BaseModel{
        //// TODO: 2017/4/12 增加新的方法以从数据库获取信息
        Observable<PagesPickerBean<IBaseBean>> getRecommendWorksFromLocation();
        Observable<PagesPickerBean<IBaseBean>> getRecommendWorksFromNetWork(int page);
    }
}
