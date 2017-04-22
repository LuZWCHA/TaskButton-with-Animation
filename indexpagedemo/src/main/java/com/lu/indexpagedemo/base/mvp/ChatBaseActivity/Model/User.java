package com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by 陆正威 on 2017/4/19.
 */

public class User implements IUser {

    private Long id;
    private String nickname;
    private String password;
    private String email;
    private String telephone;
    private String name;
    private String IDcard;
    private boolean isConfirm;
    private int gender;
    private String location;
    private int type;
    private int perimission;
    private int money;
    private String bandCard;
    private String alipay;
    private String weixin;
    private String qq;
    private boolean isOnline;
    private String regTime;
    private int count;
    private String avatarPath;
    private String token;
    private boolean isToken;
    private int accessFailedCount;
    private boolean isLock;
    private String LockTime;
    private String introduction;
    //19960221L,"陆正威",null,1,true

    public User(Long id,String nickname,String avatarPath,int type,boolean isOnline){
        this(id,nickname,avatarPath,type,isOnline,2);
    }

    public User(Long id,String nickname,String avatarPath,int type,boolean isOnline,int perimission){
        this(id,nickname,"123456","1183424701","15867954565",nickname,"",false,0,"浙江省杭州",2,perimission,20000,"",
        "","","",isOnline,"",0,avatarPath,"",false,0,false,"","");
    }

    public User(Long id, String nickname, String password, String email, String telephone, String name, String IDcard, boolean isConfirm, int gender, String location, int type, int perimission, int money, String bandCard, String alipay, String weixin, String qq, boolean isOnline, String regTime, int count, String avatarPath, String token, boolean isToken, int accessFailedCount, boolean isLock, String lockTime, String introduction) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.name = name;
        this.IDcard = IDcard;
        this.isConfirm = isConfirm;
        this.gender = gender;
        this.location = location;
        this.type = type;
        this.perimission = perimission;
        this.money = money;
        this.bandCard = bandCard;
        this.alipay = alipay;
        this.weixin = weixin;
        this.qq = qq;
        this.isOnline = isOnline;
        this.regTime = regTime;
        this.count = count;
        this.avatarPath = avatarPath;
        this.token = token;
        this.isToken = isToken;
        this.accessFailedCount = accessFailedCount;
        this.isLock = isLock;
        LockTime = lockTime;
        this.introduction = introduction;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public boolean isConfirm() {
        return isConfirm;
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPerimission() {
        return perimission;
    }

    public void setPerimission(int perimission) {
        this.perimission = perimission;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getBandCard() {
        return bandCard;
    }

    public void setBandCard(String bandCard) {
        this.bandCard = bandCard;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isToken() {
        return isToken;
    }

    public void setToken(boolean token) {
        isToken = token;
    }

    public int getAccessFailedCount() {
        return accessFailedCount;
    }

    public void setAccessFailedCount(int accessFailedCount) {
        this.accessFailedCount = accessFailedCount;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public String getLockTime() {
        return LockTime;
    }

    public void setLockTime(String lockTime) {
        LockTime = lockTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avatarPath;
    }
}
