package com.lu.indexpagedemo.view.viewholders;

import android.support.annotation.NonNull;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Constant;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.ListShowBean;
import com.lu.indexpagedemo.view.adapters.BaseViewHolder;

public class RecommendViewHolder extends BaseViewHolder<ListShowBean> {

    private int usersize;
    private int workheight;

    public RecommendViewHolder(View view) {
        super(view);
        getView(R.id.imagedraweeview);
        getView(R.id.userdrweeview);
        getView(R.id.titletextview);
        getView(R.id.contenttextview);
        usersize = Utils.dip2px(64);
        workheight = Utils.dip2px(220);

        addOnClickListener(itemView.getId());
    }

    @Override
    public void setUpViews(@NonNull final ListShowBean listShowBean) {
        Utils.load(listShowBean.getAvatarPath(), (SimpleDraweeView) getView(R.id.userdrweeview), usersize, usersize);
        Utils.load(listShowBean.getImage(), (SimpleDraweeView) getView(R.id.imagedraweeview), Constant.screenwithpx, workheight);
        setText(R.id.titletextview, listShowBean.getTitle());
        setText(R.id.contenttextview, listShowBean.getNickname());
    }

}