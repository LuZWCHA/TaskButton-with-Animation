package com.lu.indexpagedemo.bean;

import com.lu.indexpagedemo.base.Tools.TimeUtils;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.bean.factories.RecyclerViewBeanFactory;
import com.lu.indexpagedemo.view.simpleviews.fixtures.DialogsFixtures;
import com.lu.indexpagedemo.view.simpleviews.fixtures.MessagesFixtures;


/**
 * Created by 陆正威 on 2017/4/20.
 */

public class ArticleBean implements IBaseBean{

    private User user;
    private String title;
    private String article;
    private String time;
    private String imageUrl;
    private int beenlikedNum;
    private int chattingNum;

    public ArticleBean(User user, String title, String article, String time, String imageUrl,int beenlikedNum,int chattingNum) {
        this.user = user;
        this.title = title;
        this.article= article;
        this.time = time;
        this.imageUrl = imageUrl;
        this.beenlikedNum = beenlikedNum;
        this.chattingNum = chattingNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public static ArticleBean makeArticleBean(){

        return new ArticleBean(DialogsFixtures.getUser(),"THIS IS A TEST TITLE","no centent now,please wiat...",
                TimeUtils.getCurTimeMDH(), MessagesFixtures.getArticleImage(),(int)(Math.random()*100),(int)(Math.random()*100));
    }

    @Override
    public String toString() {
        return "ArticleBean{" +
                "user=" + user +
                ", article='" + article + '\'' +
                ", time='" + time + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int type(RecyclerViewBeanFactory myRecyclerViewBeanFactory) {
        return 0;
    }

    @Override
    public long getUID() {
        return 0;
    }

    public int getBeenlikedNum() {
        return beenlikedNum;
    }

    public void setBeenlikedNum(int beenlikedNum) {
        this.beenlikedNum = beenlikedNum;
    }

    public int getChattingNum() {
        return chattingNum;
    }

    public void setChattingNum(int chattingNum) {
        this.chattingNum = chattingNum;
    }
}
