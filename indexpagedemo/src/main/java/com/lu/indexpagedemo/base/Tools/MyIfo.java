package com.lu.indexpagedemo.base.Tools;

import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;

/**
 * Created by 陆正威 on 2017/4/19.
 */

public class MyIfo {
    private final static MyIfo myIfo = new MyIfo();
    private User self;

    public static MyIfo getMyIfo() {
        return myIfo;
    }

    private MyIfo(){
        self = new User(0L,"游客","",-1,false);
    }

    public boolean isEmpty(){
        return self.getId().equals("0");
    }

    public User getSelf() {
        return self;
    }

    public void setSelf(User user){
        self = user;
    }
    //others information
    public static void MakeUser(){

    }
}
