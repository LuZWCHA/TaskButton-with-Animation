package com.lu.welcomeloginpagedemos.contract;

import com.lu.welcomeloginpagedemos.mvp.Baseinterfaces.BaseModel;
import com.lu.welcomeloginpagedemos.mvp.Baseinterfaces.BasePresenter;
import com.lu.welcomeloginpagedemos.mvp.Baseinterfaces.BaseView;

/**
 * Created by 陆正威 on 2017/4/5.
 */

public interface UserLoginContract {
    
public interface View extends BaseView{
    String getUserName();
    String getPassWord();

}

public interface Presenter extends BasePresenter{
}

public interface Model extends BaseModel{
}


}