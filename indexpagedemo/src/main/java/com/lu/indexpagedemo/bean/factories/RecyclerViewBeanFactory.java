package com.lu.indexpagedemo.bean.factories;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.bean.ListShowBean;
import com.lu.indexpagedemo.bean.MidPicBean;
import com.lu.indexpagedemo.bean.MorePicBean;
import com.lu.indexpagedemo.bean.WorkBean;
import com.lu.indexpagedemo.view.adapters.BaseViewHolder;
import com.lu.indexpagedemo.view.viewholders.DesignerViewHolder;
import com.lu.indexpagedemo.view.viewholders.RecommendViewHolder;
import com.lu.indexpagedemo.view.viewholders.MidImageViewHolder;
import com.lu.indexpagedemo.view.viewholders.MoreImageViewHolder;

/**
 * Created by 陆正威 on 2017/4/4.
 */

public class RecyclerViewBeanFactory implements IBeanFactory,IViewHolderFactory {
    private static final int RECOMMEND_TYPE1 = R.layout.recyclerview_item_bpst2;
    private static final int RECOMMEND_TYPE2 = R.layout.recyclerview_item_mbnt;
    private static final int DESINER_TYPE = R.layout.recyclerview_item_bpst;
    private static final int INNERPIC_TYPE = R.layout.recyclerview_inneritem_mpnt;


    //通过Bean绑定layout
    @Override
    public int select(MorePicBean morePicBean) {
        return RECOMMEND_TYPE2;
    }

    @Override
    public int select(ListShowBean listShowBean) {
            return RECOMMEND_TYPE1;
    }

    @Override
    public int select(MidPicBean midPicBean) {
        return INNERPIC_TYPE;
    }

    @Override
    public int select(DesignerBean designerBean) {
        return DESINER_TYPE;
    }
    @Override
    public int select(WorkBean workBean) {
        return RECOMMEND_TYPE1;
    }

    //通过layout绑定ViewHolder
    @Override
    public BaseViewHolder createViewHolder(int type, View itemview, BaseQuickAdapter.OnItemChildClickListener childlistener, BaseQuickAdapter.OnItemClickListener listener) {
        if(type == RECOMMEND_TYPE2)
            return new MoreImageViewHolder(itemview,childlistener);
        else if(type == DESINER_TYPE)
            return new DesignerViewHolder(itemview);
        else if(type == RECOMMEND_TYPE1)
            return new RecommendViewHolder(itemview);
        else if(type == INNERPIC_TYPE)
            return new MidImageViewHolder(itemview);
        else
            return null;
    }



}
