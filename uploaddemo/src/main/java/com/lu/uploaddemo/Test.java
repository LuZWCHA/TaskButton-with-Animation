package com.lu.uploaddemo;

import java.util.List;

/**
 * Created by 陆正威 on 2017/4/5.
 */

public class Test {


    /**
     * message : 发送成功
     * state : 1
     * data : [{"id":1,"nickname":"","title":"woshi","content":"jfdska","time":"2017-04-05 20:05:17","star":0,"collection":0,"image":"/owap/public/patent/20170405/5a5c92bbb1b0f4a11234ccd8ee9fbe4a.jpg"},{"id":2,"nickname":null,"title":"wo","content":"laihuab","time":"2017-04-05 20:46:51","star":0,"collection":0,"image":"/owap/public/patent/20170405/7a94845215ecc84ca7f83d180b2a8c37.png"},{"id":3,"nickname":null,"title":"adad","content":"ad","time":"2017-04-05 20:50:37","star":0,"collection":0,"image":"/owap/public/patent/20170405/a207d2b3c7f08e45cb9024ca6f51c018.png"},{"id":4,"nickname":null,"title":"1312","content":"12","time":"2017-04-05 20:52:13","star":0,"collection":0,"image":"/owap/public/patent/20170405/586df150e7dda3c3466ee07a7c984699.png"},{"id":5,"nickname":null,"title":"laihuabo","content":"ksj","time":"2017-04-05 20:54:40","star":0,"collection":0,"image":"/owap/public/patent/20170405/e9c23f383ce18b6ac61e0bf4289e57f9.jpg"},{"id":6,"nickname":null,"title":"我是","content":"你好","time":"2017-04-05 21:08:06","star":0,"collection":0,"image":"/owap/public/patent/20170405/5dc7de19a0eab27797890c6ee384b200.png"},{"id":7,"nickname":null,"title":"Mytest","content":"dad","time":"2017-04-05 21:17:48","star":0,"collection":0,"image":"/owap/public/patent/20170405/c98085b5f3698665f144fcc18ccc1ab7.png"},{"id":8,"nickname":null,"title":"hi","content":"终于可以测试过了","time":"2017-04-05 21:35:44","star":0,"collection":0,"image":"/owap/public/patent/20170405/58cae4c8badb3e7e4afbf485bd8e02b6.png"}]
     */

    private String message;
    private int state;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

    }
}
