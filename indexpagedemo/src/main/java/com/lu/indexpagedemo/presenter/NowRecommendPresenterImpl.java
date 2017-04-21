package com.lu.indexpagedemo.presenter;
import com.lu.indexpagedemo.base.mvp.BasePresenterImpl;
import com.lu.indexpagedemo.contract.NowRecommendContract;
import com.lu.indexpagedemo.model.NowRecommendModelImpl;

/**
* Created by 陆正威 on 2017/04/20
*/

public class NowRecommendPresenterImpl extends BasePresenterImpl<NowRecommendContract.View>{
    NowRecommendContract.Model model;

    public NowRecommendPresenterImpl(){
        model = new NowRecommendModelImpl();
    }

}