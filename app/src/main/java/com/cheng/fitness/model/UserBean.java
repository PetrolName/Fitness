package com.cheng.fitness.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc: 用户
 */
@Entity
public class UserBean {
    @Id(autoincrement = true)
    private Long id;
    //用户头像
    private String avatar;
    //用户昵称
    private String nickname;
    //用户密码
    private String password;
    //用户性别
    private String gender;
    //判断用户是不是第一次登录，初始化数据要用
    private boolean isFirstTime;
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean getIsFirstTime() {
        return this.isFirstTime;
    }
    public void setIsFirstTime(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }
    @Generated(hash = 1499882956)
    public UserBean(Long id, String avatar, String nickname, String password,
            String gender, boolean isFirstTime) {
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        this.isFirstTime = isFirstTime;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
}
