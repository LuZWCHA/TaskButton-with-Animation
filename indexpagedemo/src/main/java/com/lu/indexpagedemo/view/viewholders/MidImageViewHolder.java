package com.lu.indexpagedemo.view.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.MidPicBean;
import com.lu.indexpagedemo.view.adapters.BaseViewHolder;

/**
 * Created by 陆正威 on 2017/4/8.
 */

public class MidImageViewHolder extends BaseViewHolder<MidPicBean>{

    private SimpleDraweeView latestdraweeView;
    private AppCompatImageButton likeButton;

    public MidImageViewHolder(View itemView) {
        super(itemView);
        latestdraweeView = getView(R.id.innerdrweeview);
        likeButton = getView(R.id.innerbutton);
        addOnClickListener(likeButton.getId());
        addOnClickListener(latestdraweeView.getId());
    }

    @Override
    public void setUpViews(@NonNull final MidPicBean baseBean) {
        Utils.load(baseBean.getImageurl(),latestdraweeView,Utils.dip2px(160),Utils.dip2px(200));
        latestdraweeView.setTag(baseBean);
        likeButton.setTag(baseBean);
    }
}
