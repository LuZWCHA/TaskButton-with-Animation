package com.lu.mytaskbutton;

import android.animation.TimeInterpolator;
import android.util.Log;

/**
 * Created by 陆正威 on 2017/3/14.
 */

public class ExtensionInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        Log.i(this.getClass().getName(),input+"");
        return (float) Math.sin(input * Math.PI  * 0.99 + 0.01 *Math.PI);
    }
}

