package com.lu.indexpagedemo.contract;

import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseModel;
import com.lu.indexpagedemo.base.mvp.Baseinterfaces.BaseView;

/**
 * Created by 陆正威 on 2017/4/22.
 */

public interface RegisterContract {
public interface View extends BaseView{
    void registerSuccess();
    void registerFail();

}

public interface Model extends BaseModel{
    void tryRegister();
}


}