package com.lu.mytaskbutton;

import android.animation.TimeInterpolator;

/**
 * Created by 陆正威 on 2017/3/14.
 */

public class ExtensionInterpolator2 implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return (float) Math.cos(input * Math.PI  ) * 0.5f + 0.5f;
    }
}
