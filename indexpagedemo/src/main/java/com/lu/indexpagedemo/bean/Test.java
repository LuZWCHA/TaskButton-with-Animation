package com.lu.indexpagedemo.bean;

import java.util.List;

/**
 * Created by 陆正威 on 2017/4/4.
 */

public class Test {

    /**
     * message : 发送成功
     * state : 1
     * data : [{"id":10,"nickname":"zimo","title":"woshi","image":"/owap/public/patent/20170407/16ca96b73f83ad0211602a324984ad72.jpg","avatarPath":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg"},{"id":11,"nickname":"zimo","title":"1","image":"/owap/public/patent/20170407/76fc48d11e2e1e9797cd92d2c3fc7753.png","avatarPath":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg"},{"id":12,"nickname":"zimo","title":"我是","image":"/owap/public/patent/20170407/cb5c9fd7b4d8c1693b225f5d73afb2c4.jpg","avatarPath":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg"},{"id":13,"nickname":"zimo","title":"2","image":"/owap/public/patent/20170407/83aa4ebea0b8c20791dcd3ec0fd9b65f.png","avatarPath":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg"},{"id":14,"nickname":"zimo","title":"你好","image":"/owap/public/patent/20170407/ebaf5b2aa16433d874af23b9ea7f26f1.jpg","avatarPath":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg"},{"id":15,"nickname":"zimo","title":"3","image":"/owap/public/patent/20170407/49fd0145b6ad8b1aad856786a7c4c460.png","avatarPath":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg"},{"id":16,"nickname":"zimo","title":"5","image":"/owap/public/patent/20170407/a0fd91abf1f5468df40ac3735292d0ac.png","avatarPath":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg"},{"id":17,"nickname":"zimo","title":"4","image":"/owap/public/patent/20170407/c8a25ebdc8d87d89589359a9b8f43d61.png","avatarPath":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg"},{"id":18,"nickname":"zimo","title":"6","image":"/owap/public/patent/20170407/75326a063fae5ecf8d0880e95842c75a.png","avatarPath":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg"},{"id":19,"nickname":"zimo","title":"hello","image":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg","avatarPath":"/owap/public/patent/20170407/d9b576db13d5fc8cf0169c8eb7d2d78d.jpg"}]
     * next : true
     */

    private String message;
    private int state;
    private boolean next;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

    }
}
