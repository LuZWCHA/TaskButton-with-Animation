package com.lu.indexpagedemo.Api;

import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;
import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.bean.ListShowBean;
import com.lu.indexpagedemo.bean.WorkBean;
import com.lu.indexpagedemo.bean.base.HttpResponseBase;
import com.lu.indexpagedemo.bean.PagesPickerBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by 陆正威 on 2017/4/5.
 */

public interface ApiStore {

    @GET("/owap/public/patentList/{page}")
    Observable<HttpResponseBase<PagesPickerBean<ListShowBean>>> getRescommendList(@Path("page") int page);

    @GET("/owap/public/patentList/{page}")
    Observable<HttpResponseBase<PagesPickerBean<ListShowBean>>> getWorkBeanList(@Path("page") int page);

    @GET("/owap/public/patent/{id}")
    Observable<HttpResponseBase<WorkBean>> getWorkBeanDetails(@Path("id") long id);

    @GET("/owap/public/designer/{id}")
    Observable<HttpResponseBase<User>> getUserbyWorkId(@Path("id")long id);

    // TODO: 2017/4/11 数据模拟先用ListShowBean代替
    @GET("/owap/public/patentList/{page}")
    Observable<HttpResponseBase<PagesPickerBean<DesignerBean>>> getDesignerList(@Path("page") int page);

    @POST()
    Observable<HttpResponseBase<String>> tryLogin();

    @POST()
    Observable<HttpResponseBase<String>> tryRegister();
}
