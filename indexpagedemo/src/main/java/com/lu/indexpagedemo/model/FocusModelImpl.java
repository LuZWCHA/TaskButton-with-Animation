package com.lu.indexpagedemo.model;
import com.lu.indexpagedemo.base.retrofit.RetrofitClient;
import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.contract.FocusContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
* Created by 陆正威 on 2017/04/15
*/

public class FocusModelImpl implements FocusContract.Model{

    @Override
    public Observable<List<DesignerBean>> getFocusfromNetWork() {

        DesignerBean designerBean = new DesignerBean();
        designerBean.setAvatarPath("https://images.pexels.com/photos/262372/pexels-photo-262372.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
        designerBean.setId(10L);
        designerBean.setImage("https://images.pexels.com/photos/262372/pexels-photo-262372.jpeg?w=940&h=650&auto=compress&cs=tinysrgb");
        designerBean.setNickname("Lucky lu");
        designerBean.setTitle("hi every");

        List<DesignerBean> list = new ArrayList<>();
        list.add(designerBean);

        return Observable.just(list);
    }

    @Override
    public Observable<List<DesignerBean>> getFocusfromLocation() {
        return null;
    }

    @Override
    public void uploadChange(int Fouceid) {

    }
}