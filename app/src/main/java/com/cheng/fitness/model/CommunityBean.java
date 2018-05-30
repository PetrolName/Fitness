package com.cheng.fitness.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * author: PengCheng
 * time: 2018/5/29 0029
 * desc: 社区实体
 */
@Entity
public class CommunityBean implements Serializable{
    @Id(autoincrement = true)
    private Long id;
    //用户名字
    private String name;
    //用户头像
    private String avatar;
    //内容
    private String content;
    //图片
    private String image;
    //评论
    private int comment;
    //赞
    private int like;
    //时间
    private String time;
    //当前用户是否点过赞
    private boolean isLike;
    public int getLike() {
        return this.like;
    }
    public void setLike(int like) {
        this.like = like;
    }
    public int getComment() {
        return this.comment;
    }
    public void setComment(int comment) {
        this.comment = comment;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
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
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public boolean getIsLike() {
        return this.isLike;
    }
    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }
    @Generated(hash = 407466972)
    public CommunityBean(Long id, String name, String avatar, String content,
            String image, int comment, int like, String time, boolean isLike) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.content = content;
        this.image = image;
        this.comment = comment;
        this.like = like;
        this.time = time;
        this.isLike = isLike;
    }
    @Generated(hash = 435866697)
    public CommunityBean() {
    }

}
