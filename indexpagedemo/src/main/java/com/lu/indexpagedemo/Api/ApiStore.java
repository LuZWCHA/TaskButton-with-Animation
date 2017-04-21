package com.lu.indexpagedemo.Api;

import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.bean.RecommendBean;
import com.lu.indexpagedemo.bean.base.HttpResponseBase;
import com.lu.indexpagedemo.bean.PagesPickerBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by 陆正威 on 2017/4/5.
 */

public interface ApiStore {

    @GET("/owap/public/patentList/{page}")
    Observable<HttpResponseBase<PagesPickerBean<RecommendBean>>> getRescommendList(@Path("page") int page);

    // TODO: 2017/4/11 数据模拟先用RecommendBean代替
    @GET("/owap/public/patentList/{page}")
    Observable<HttpResponseBase<PagesPickerBean<DesignerBean>>> getDesignerList(@Path("page") int page);

}
