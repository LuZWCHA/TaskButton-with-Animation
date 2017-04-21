package com.lu.mydemos.view.asdada;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by 陆正威 on 2017/4/17.
 */

public class User implements IUser {
    String id;
    String name;
    String avator;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avator;
    }
}
