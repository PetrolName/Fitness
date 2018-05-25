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
    //用户名字
    private String name;
    //用户头像
    private String avatar;
    //用户昵称
    private String nickname;
    //用户性别
    private String gender;
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
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1225980216)
    public UserBean(Long id, String name, String avatar, String nickname,
            String gender) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.nickname = nickname;
        this.gender = gender;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
}
