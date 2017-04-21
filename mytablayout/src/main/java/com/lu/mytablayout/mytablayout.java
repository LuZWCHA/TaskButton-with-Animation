package com.lu.mytablayout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import java.lang.reflect.Field;

/**
 * Created by 陆正威 on 2017/3/31.
 */

public class mytablayout extends TableLayout{

    public mytablayout(Context context) {
        super(context);
    }

    public mytablayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIndicator(int leftDip, int rightDip) {
        Class<?> tabLayout = super.getClass();
        Field tabStrip = null;

    }
}
