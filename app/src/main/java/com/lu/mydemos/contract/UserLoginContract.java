package com.lu.mydemos.contract;

import android.support.design.widget.TabLayout;

import com.lu.mydemos.mvp.BasePresenterImpl;
import com.lu.mydemos.mvp.Baseinterfaces.BaseModel;
import com.lu.mydemos.mvp.Baseinterfaces.BaseModelCallBack;
import com.lu.mydemos.mvp.Baseinterfaces.BasePresenter;
import com.lu.mydemos.mvp.Baseinterfaces.BaseView;
import com.lu.mydemos.mvp.MvpBaseActivity;
import com.lu.mydemos.presenter.UserLoginPresenterImpl;
import com.lu.mydemos.view.Login;

/**
 * Created by 陆正威 on 2017/3/31.
 */



public interface UserLoginContract {
    
public interface View extends BaseView {
    void setButtonColor(int buttonColor);
}

public interface Presenter extends BasePresenter{
    void login();
}

public interface Model extends BaseModel {

}

public interface ModelCallBack extends BaseModelCallBack {
}


}