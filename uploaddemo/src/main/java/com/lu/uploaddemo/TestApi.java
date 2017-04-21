package com.lu.uploaddemo;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by 陆正威 on 2017/4/5.
 */
//BaseUrl:www.lhbzimo.cn/
public interface TestApi {

    @Multipart
    @POST("/owap/public/patent/upload")
    Observable<ResponseBody> uploadImage(@Part("image\"; filename=\"lu.png\"") RequestBody file, @Query("title") String title,@Query("content") String content);
}
