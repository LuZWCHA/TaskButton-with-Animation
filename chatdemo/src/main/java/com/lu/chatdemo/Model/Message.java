package com.lu.chatdemo.Model;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;
import com.stfalcon.chatkit.commons.models.MessageContentType;

import java.util.Date;

/**
 * Created by 陆正威 on 2017/4/19.
 */

public class Message implements IMessage, MessageContentType.Image{

    private Long id;

    private String text;
    private User user;
    private Date time;
    private String imageUrl;

    public Message(){

    }

    public Message(Long id, String text, User user, Date time, String imageUrl) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
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
        return time;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(Date time) {
        this.time = time;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
