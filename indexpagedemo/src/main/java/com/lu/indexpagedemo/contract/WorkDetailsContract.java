package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;
import com.lu.indexpagedemo.bean.WorkBean;

import io.reactivex.Observable;

/**
 * Created by 陆正威 on 2017/4/21.
 */

public interface WorkDetailsContract {
    
public interface View extends BaseView{
    void showLoading();
    void hideLoading();
    void updateDetails(WorkBean workBean);
    void updateUser(User user);
}

public interface Model extends BaseModel{
    Observable<WorkBean> getWorkDetails(long id);
    Observable<User> getUserbyWorkId(long id);
}


}