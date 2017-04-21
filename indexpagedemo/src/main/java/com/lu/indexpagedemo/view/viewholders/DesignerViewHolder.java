package com.lu.indexpagedemo.view.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.AppManager;
import com.lu.indexpagedemo.base.Tools.Constant;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.view.adapters.BaseViewHolder;

/**
 * Created by 陆正威 on 2017/4/11.
 */

public class DesignerViewHolder extends BaseViewHolder<DesignerBean> {

    private int usersize;
    private int workheight;

    public DesignerViewHolder(View view) {
        super(view);
        getView(R.id.imagedraweeview);
        getView(R.id.userdrweeview);
        getView(R.id.titletextview);
        getView(R.id.contenttextview);
        usersize = Utils.dip2px(64);
        workheight = Utils.dip2px(220);
    }

    @Override
    public void setUpViews(@NonNull final DesignerBean designerBean) {
        Utils.load(designerBean.getAvatarPath(), (SimpleDraweeView) getView(R.id.userdrweeview), usersize, usersize);
        Utils.load(designerBean.getImage(), (SimpleDraweeView) getView(R.id.imagedraweeview), Constant.screenwithpx, workheight);
        setText(R.id.titletextview,designerBean.getTitle());
        setText(R.id.contenttextview,designerBean.getNickname());
    }
}
