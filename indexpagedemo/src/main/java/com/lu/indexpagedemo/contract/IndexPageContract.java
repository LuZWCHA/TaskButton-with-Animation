package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 陆正威 on 2017/4/6.
 */

public interface IndexPageContract {
    interface View extends BaseView{
        // TODO: 2017/4/12 增加从本地获取信息
        void updateRollViewData(List<String> newdata);
        void updateOne();
        void hideLoadingHeader();
    }

    interface Model extends BaseModel{

        // TODO: 2017/4/12 增加从本地获取信息
        Observable<ArrayList<String>> getRollViewPagerFromLocation();
        Observable<ArrayList<String>> getRollViewPagerFromNetWork();
    }


}