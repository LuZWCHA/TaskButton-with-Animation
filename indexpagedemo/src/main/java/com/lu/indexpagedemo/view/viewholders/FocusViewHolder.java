package com.lu.indexpagedemo.view.viewholders;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.lu.indexpagedemo.R;

/**
 * Created by 陆正威 on 2017/4/15.
 */


//simpeviewholder
public class FocusViewHolder extends BaseViewHolder {
    public FocusViewHolder(View view) {
        super(view);
        addOnClickListener(R.id.disfocus);
        addOnClickListener(R.id.focus_item);
    }
}
