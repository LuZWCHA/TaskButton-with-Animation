package com.lu.indexpagedemo.view.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Constant;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.RecommendBean;
import com.lu.indexpagedemo.view.adapters.BaseViewHolder;

public class RecommendViewHolder extends BaseViewHolder<RecommendBean> {

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
    public void setUpViews(@NonNull final RecommendBean recommendBean) {
        Utils.load(recommendBean.getAvatarPath(), (SimpleDraweeView) getView(R.id.userdrweeview), usersize, usersize);
        Utils.load(recommendBean.getImage(), (SimpleDraweeView) getView(R.id.imagedraweeview), Constant.screenwithpx, workheight);
        setText(R.id.titletextview,recommendBean.getTitle());
        setText(R.id.contenttextview,recommendBean.getNickname());
    }

}