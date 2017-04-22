package com.lu.indexpagedemo.bean.factories;

import com.lu.indexpagedemo.bean.DesignerBean;
import com.lu.indexpagedemo.bean.ListShowBean;
import com.lu.indexpagedemo.bean.MidPicBean;
import com.lu.indexpagedemo.bean.MorePicBean;
import com.lu.indexpagedemo.bean.WorkBean;

/**
 * Created by 陆正威 on 2017/4/4.
 */

public interface IBeanFactory {
    int select(MorePicBean morePicBean);
    int select(ListShowBean listShowBean);
    int select(MidPicBean midPicBean);
    int select(DesignerBean designerBean);
    int select(WorkBean workBean);
}
