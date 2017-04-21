package com.stfalcon.chatkit.utils;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by 陆正威 on 2017/4/18.
 */

public class FrescoAutoFit {

    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, final String imagePath, final int imageWidth) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null)
                    return;
                if (imageInfo.getHeight() == 0 || imageInfo.getHeight() == 0)
                    return;

                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();


                if (imageWidth > width) {
                    layoutParams.width = width;
                    layoutParams.height = (int) ((float) (height) / (float) width);
                    simpleDraweeView.setLayoutParams(layoutParams);
                } else if (imageWidth > 0) {
                    layoutParams.width = imageWidth;
                    layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
                    simpleDraweeView.setLayoutParams(layoutParams);
                }
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                Log.d("TAG", "Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(imagePath))
                .build();
        PipelineDraweeController pipelineDraweeController = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(simpleDraweeView.getController())
                .setControllerListener(controllerListener)
                .setAutoPlayAnimations(true)
                .build();
        simpleDraweeView.setController(pipelineDraweeController);
    }

    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, final String imagePath) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null)
                    return;
                if (imageInfo.getHeight() == 0 || imageInfo.getHeight() == 0)
                    return;

                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();


                //if(imageWidth > width) {
                //layoutParams.width =  width;
                layoutParams.height = (int) ((float) (height) / (float) width);
                simpleDraweeView.setLayoutParams(layoutParams);
//                simpleDraweeView.requestLayout();
                //}else if(imageWidth > 0){
//                    layoutParams.width =imageWidth;
//                    layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
//                    simpleDraweeView.setLayoutParams(layoutParams);
//                }
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                Log.d("TAG", "Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(imagePath))
                .build();
        PipelineDraweeController pipelineDraweeController = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(simpleDraweeView.getController())
                .setControllerListener(controllerListener)
                .setAutoPlayAnimations(true)
                .build();
        simpleDraweeView.setController(pipelineDraweeController);
    }



}
