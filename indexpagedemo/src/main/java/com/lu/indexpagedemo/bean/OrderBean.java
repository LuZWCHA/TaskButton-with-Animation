package com.lu.indexpagedemo.bean;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by 陆正威 on 2017/4/20.
 */
//a order include work bean ,address bean, user bean,,designer bean and the type of choosing designer, int state the state of the order;
//@Entity
public class OrderBean {

    @SerializedName("id")
    private Long id;//for network store;

    //@ToOne
    @SerializedName("user")
    private User user;//for a user type == -1 is empty

    //@ToOne
    @SerializedName("work")
    private WorkBean work;//

    //@ToOne
    @SerializedName("address")
    private AddressBean address;

    //@ToOne
    @SerializedName("type")
    private int type;

    @SerializedName("state")
    private int state;

    @SerializedName("designer")
    private DesignerBean designer;

    @SerializedName("time")
    private String time;

    @SerializedName("fake_price")
    private int fakePrice;

    @SerializedName("number")
    private int num;

    @SerializedName("size")
    private int size;

    @SerializedName("material")
    private int material;

    //the default constructor
    public OrderBean(){
        this(new User(-1L,"","",-1,false),new WorkBean(),new AddressBean(),-1,-1,new DesignerBean());
    }

    public OrderBean(User user, WorkBean work, AddressBean address, int type, int state, DesignerBean designer) {
        this.user = user;
        this.work = work;
        this.address = address;
        this.type = type;
        this.state = state;
        this.designer = designer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkBean getWork() {
        return work;
    }

    public void setWork(WorkBean work) {
        this.work = work;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public DesignerBean getDesigner() {
        return designer;
    }

    public void setDesigner(DesignerBean designer) {
        this.designer = designer;
    }
}
