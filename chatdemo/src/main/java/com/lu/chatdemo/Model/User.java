package com.lu.chatdemo.Model;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by 陆正威 on 2017/4/19.
 */

public class User implements IUser {

    private Long uid;

    private String name;
    private String avatar;
    //for designer type is 0 ;for simple user type is 1 ;for server type is 2;for none is -1;
    private int type;
    private boolean online;

    public User(Long id, String name, String avatar, int type, boolean online) {
        this.uid = id;
        this.name = name;
        this.avatar = avatar;
        this.type = type;
        this.online = online;
    }

    public Long getUid(){
        return uid;
    }

    @Override
    public String getId() {
        return String.valueOf(uid);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public boolean isOnline() {
        return online;
    }

    public int getType() {
        return type;
    }
}
