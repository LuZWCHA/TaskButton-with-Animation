package com.lu.indexpagedemo.view.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.AbsListView;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 陆正威 on 2017/4/7.
 */

public class MyRecyclerView extends RecyclerView {
    private MyRecyclerViewScrollListener myRecyclerViewScrollListener;
    private int preScrollState = RecyclerView.SCROLL_STATE_IDLE;
    public MyRecyclerView(Context context) {
        super(context);
    }


    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //init();
    }

    @Override
    public void onScrollStateChanged(int newState) {
        super.onScrollStateChanged(newState);
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE://停止滑动
                if (Fresco.getImagePipeline().isPaused())
                    Fresco.getImagePipeline().resume();
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                if (preScrollState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //触摸滑动不需要加载
                    Fresco.getImagePipeline().pause();
                } else {
                    //触摸滑动需要加载
                    if (Fresco.getImagePipeline().isPaused())
                        Fresco.getImagePipeline().resume();
                }
                break;
            case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                Fresco.getImagePipeline().pause();
                break;
        }
        preScrollState = newState;
    }
}