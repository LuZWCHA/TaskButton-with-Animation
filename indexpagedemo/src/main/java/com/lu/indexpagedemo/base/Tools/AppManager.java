package com.lu.indexpagedemo.base.Tools;

import android.app.ActivityManager;
import android.app.Application;

import com.com.sky.downloader.greendao.DBManager;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import com.imnjh.imagepicker.PickerConfig;
import com.imnjh.imagepicker.SImagePicker;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;
import com.lu.indexpagedemo.base.retrofit.RetrofitClient;

import java.io.File;

/**
 * Created by 陆正威 on 2017/4/6.
 */

public class AppManager extends Application {
    private static AppManager context;
    private ActivityManager activityManager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        ScreenParamsInit();
        FrescoInit();
        ImagePickerInit();
        GreenDaoInit();
        QSdkApp.Init(this);

        MyselfInit();
    }
    public static AppManager app()
    {
        return context;
    }

    private void ScreenParamsInit(){
        int a[] = Utils.getScreenSize();
        Constant.screenwithpx = a[0];Constant.screenheightpx = a[1];
        Constant.screenwithdp = Utils.px2dip(Constant.screenwithpx);
        Constant.screenheightdp = Utils.px2dip(Constant.screenheightpx);
    }

    private void MyselfInit(){

        // TODO: 2017/4/19 test only if login application done there will be new way to init;
        if(MyIfo.getMyIfo().isEmpty()){
            MyIfo.getMyIfo().setSelf(new User(19960221L,"陆正威",null,1,true));
        }
    }

    private void GreenDaoInit(){
        DBManager.initialize(this);
    }

    private void ImagePickerInit(){
        SImagePicker.init(new PickerConfig.Builder().setAppContext(this)
                .setImageLoader(new FrescoImageLoader())
                .setToolbaseColor(getResources().getColor(R.color.colorPrimaryDark))
                .build());
    }

    private void FrescoInit(){
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setMaxCacheSize(20 * ByteConstants.MB)
                .setBaseDirectoryPathSupplier(new Supplier<File>() {
                    @Override
                    public File get() {
                        return getCacheDir();
                    }
                })
                .build();

        final FrescoCacheParams bitmapCacheParams = new FrescoCacheParams(activityManager);
        //Set<RequestListener> listeners = new HashSet<>();
        ImagePipelineConfig imagePipelineConfig = OkHttpImagePipelineConfigFactory.newBuilder(this, RetrofitClient.getInstance().getmOkHttpClient())
                .setMainDiskCacheConfig(diskCacheConfig)
                .setBitmapMemoryCacheParamsSupplier(bitmapCacheParams)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this,imagePipelineConfig);
    }
}
