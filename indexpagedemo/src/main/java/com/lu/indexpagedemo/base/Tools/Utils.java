package com.lu.indexpagedemo.base.Tools;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.lu.indexpagedemo.base.retrofit.RetrofitClient;

/**
 * Created by 陆正威 on 2017/4/6.
 */

public class Utils {

    public static void TRANSPARENT(Window window){
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    //public final static String BASE_URL = RetrofitClient.BASE_URL;
    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) AppManager.app()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable();
    }

    public static void MakeTost(boolean isLong,String text){
        Toast.makeText(AppManager.app(),text,isLong ? Toast.LENGTH_LONG:Toast.LENGTH_SHORT).show();
    }

    public static String AddBaseUrl(String url){
        url = RetrofitClient.BASE_URL.concat(url.substring(1));
        return url;
    }

    static int[] getScreenSize() {
        WindowManager wm = (WindowManager) AppManager.app().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
    }

    public static int dip2px(float dpValue) {
        final float scale = AppManager.app().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = AppManager.app().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void load(@NonNull String uri,@NonNull SimpleDraweeView draweeView, BasePostprocessor processor, int width, int height, BaseControllerListener<ImageInfo> listener ){
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(uri))
                .setResizeOptions(new ResizeOptions(width,height))
                .setPostprocessor(processor)
                .build();
        PipelineDraweeController pipelineDraweeController = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(draweeView.getController())
                .setControllerListener(listener)
                .setAutoPlayAnimations(true)
                .build();
        draweeView.setController(pipelineDraweeController);

    }

    public static void load(@NonNull String uri,@NonNull SimpleDraweeView draweeView, int width, int height, ControllerListener<ImageInfo> listener ){
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(uri))
                .setResizeOptions(new ResizeOptions(width,height))
                .build();
        PipelineDraweeController pipelineDraweeController = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(draweeView.getController())
                .setControllerListener(listener)
                .setAutoPlayAnimations(true)
                .build();
        draweeView.setController(pipelineDraweeController);
    }

    public static void load(@NonNull String uri,@NonNull SimpleDraweeView draweeView, int width, int height){
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(uri))
                .setResizeOptions(new ResizeOptions(width,height))
                .build();
        PipelineDraweeController pipelineDraweeController = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(imageRequest)
                .build();
        draweeView.setController(pipelineDraweeController);
    }

    public static void reload(@NonNull String uri,@NonNull SimpleDraweeView simpleDraweeView){
        simpleDraweeView.setImageURI(uri);
    }

}
