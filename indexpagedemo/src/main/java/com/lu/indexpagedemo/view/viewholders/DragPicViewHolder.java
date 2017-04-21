package com.lu.indexpagedemo.view.viewholders;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.lu.indexpagedemo.R;

/**
 * Created by 陆正威 on 2017/4/16.
 */

public class DragPicViewHolder extends BaseViewHolder {
    public DragPicViewHolder(View view) {
        super(view);
        //addOnClickListener(R.id.custom_item_pic);
        addOnClickListener(R.id.custom_item_delete);
    }
}
