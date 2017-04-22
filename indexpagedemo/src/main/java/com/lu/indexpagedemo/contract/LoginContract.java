package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;

/**
 * Created by 陆正威 on 2017/4/22.
 */

public interface LoginContract {
    
public interface View extends BaseView{
    void loginSuccess();
    void loginFail(String errorMessage);
}

public interface Model extends BaseModel{
    void tryLogin(String name,String password);
}


}