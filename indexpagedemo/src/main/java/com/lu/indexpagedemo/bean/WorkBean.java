package com.lu.indexpagedemo.bean;

import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.bean.factories.RecyclerViewBeanFactory;

/**
 * Created by 陆正威 on 2017/4/11.
 */

public class WorkBean implements IBaseBean{
    /**
     * id : 1
     * nickname : ""
     * title : woshi
     * content : jfdska
     * time : 2017-04-05 20:05:17
     * star : 0
     * collection : 0
     * image : /owap/public/patent/20170405/5a5c92bbb1b0f4a11234ccd8ee9fbe4a.jpg
     */

    /**
     * id : 10
     * nickname : zimo
     * title : woshi
     * image : /owap/public/patent/20170407/16ca96b73f83ad0211602a324984ad72.jpg
     * avatarPath : /owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg
     */
    // TODO: 2017/4/20 尺寸和材质等待添加
    private Long id;
    private String nickname;
    private String title;
    private String image;
    private String avatarPath;
    private int price;

    public WorkBean(){
        nickname = "";
        title = "";
        image = "";
        avatarPath = "";
        price = 100;
    }

    public WorkBean(String nickname, String title, String image, String avatarPath, int price) {
        this.nickname = nickname;
        this.title = title;
        this.image = image;
        this.avatarPath = avatarPath;
        this.price = price;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
