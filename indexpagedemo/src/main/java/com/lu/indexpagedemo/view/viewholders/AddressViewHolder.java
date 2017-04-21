package com.lu.indexpagedemo.view.viewholders;

import android.support.annotation.NonNull;
import android.view.View;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.bean.AddressBean;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.view.adapters.BaseViewHolder;

/**
 * Created by 陆正威 on 2017/4/14.
 */

public class AddressViewHolder extends com.chad.library.adapter.base.BaseViewHolder {

    public AddressViewHolder(View view) {
        super(view);
        getView(R.id.address_textview);
        getView(R.id.name_phonenum_textview);
        getView(R.id.address_item_view);
        addOnClickListener(R.id.address_item_view);
        addOnLongClickListener(R.id.address_item_view);
    }

}
