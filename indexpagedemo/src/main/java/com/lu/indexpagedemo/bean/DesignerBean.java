package com.lu.indexpagedemo.bean;

import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.bean.factories.RecyclerViewBeanFactory;

/**
 * Created by 陆正威 on 2017/4/11.
 */

public class DesignerBean implements IBaseBean {

    // TODO: 2017/4/11 暂时先用RecommendBean代替一下，等待后台数据
    private Long id;
    private String nickname;
    private String title;
    private String image;
    private String avatarPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Override
    public int type(RecyclerViewBeanFactory myRecyclerViewBeanFactory) {
        return myRecyclerViewBeanFactory.select(this);
    }

    @Override
    public long getUID() {
        return id;
    }
}
