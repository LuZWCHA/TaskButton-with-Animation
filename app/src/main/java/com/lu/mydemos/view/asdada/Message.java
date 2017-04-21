package com.lu.mydemos.view.asdada;

import android.os.SystemClock;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by 陆正威 on 2017/4/17.
 */

public class Message implements IMessage {

    String id;
    String text;
    User user;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public IUser getUser() {
        return user;
    }

    @Override
    public Date getCreatedAt() {
        return new Date(com.facebook.common.time.SystemClock.get().now());
    }
}
