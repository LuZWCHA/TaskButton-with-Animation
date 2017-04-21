package com.lu.indexpagedemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.bean.base.IBaseBean;
import com.lu.indexpagedemo.bean.factories.RecyclerViewBeanFactory;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 陆正威 on 2017/4/14.
 */
@Entity
public class AddressBean implements IBaseBean, Parcelable {

    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private Long addressid;
    @Property(nameInDb = "name")
    private String realName;
    @Property(nameInDb = "sex")
    private boolean sex;
    @Property(nameInDb = "address")
    private String areaDetile;
    @Property(nameInDb = "phone")
    private String phoneNum;
    @Property(nameInDb = "code")
    private int code;
    @Property(nameInDb = "usable")
    private boolean isDefult;

    public AddressBean() {
        this.realName = "";
        this.sex = true;
        this.areaDetile = "";
        this.phoneNum = "";
        this.code = -1;
        this.isDefult = false;
    }

    public AddressBean(String realName, boolean sex, String areaDetile, String phoneNum, int code, boolean isDefult) {
        this.realName = realName;
        this.sex = sex;
        this.areaDetile = areaDetile;
        this.phoneNum = phoneNum;
        this.code = code;
        this.isDefult = isDefult;
    }

    @Generated(hash = 877149015)
    public AddressBean(Long addressid, String realName, boolean sex, String areaDetile, String phoneNum, int code,
            boolean isDefult) {
        this.addressid = addressid;
        this.realName = realName;
        this.sex = sex;
        this.areaDetile = areaDetile;
        this.phoneNum = phoneNum;
        this.code = code;
        this.isDefult = isDefult;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAreaDetile() {
        return areaDetile;
    }

    public void setAreaDetile(String areaDetile) {
        this.areaDetile = areaDetile;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isDefult() {
        return isDefult;
    }
    public void setDefult(boolean defult) {
        isDefult = defult;
    }
    @Override
    public int type(RecyclerViewBeanFactory myRecyclerViewBeanFactory) {
        return R.layout.recycleview_item_address;
    }

    @Override
    public long getUID() {
        return (realName+areaDetile+phoneNum+(sex?"man":"woman")+code).hashCode();
    }

    public Long getAddressid() {
        return this.addressid;
    }

    public void setAddressid(Long addressid) {
        this.addressid = addressid;
    }

    public boolean getSex() {
        return this.sex;
    }

    public boolean getIsDefult() {
        return this.isDefult;
    }

    public void setIsDefult(boolean isDefult) {
        this.isDefult = isDefult;
    }

    //序列化
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.addressid);
        dest.writeString(this.realName);
        dest.writeByte(this.sex ? (byte) 1 : (byte) 0);
        dest.writeString(this.areaDetile);
        dest.writeString(this.phoneNum);
        dest.writeInt(this.code);
        dest.writeByte(this.isDefult ? (byte) 1 : (byte) 0);
    }

    protected AddressBean(Parcel in) {
        this.addressid = (Long) in.readValue(Long.class.getClassLoader());
        this.realName = in.readString();
        this.sex = in.readByte() != 0;
        this.areaDetile = in.readString();
        this.phoneNum = in.readString();
        this.code = in.readInt();
        this.isDefult = in.readByte() != 0;
    }

    public static final Parcelable.Creator<AddressBean> CREATOR = new Parcelable.Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel source) {
            return new AddressBean(source);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };
}
