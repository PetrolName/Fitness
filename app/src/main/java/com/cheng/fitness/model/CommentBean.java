package com.cheng.fitness.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import com.cheng.fitness.utils.greendao.gen.DaoSession;
import com.cheng.fitness.utils.greendao.gen.CommentBeanDao;
import com.cheng.fitness.utils.greendao.gen.CommunityBeanDao;

/**
 * author: PengCheng
 * time: 2018/6/1 0001
 * desc: 评论
 */
@Entity
public class CommentBean {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String avatar;
    private String content;
    private String date;
    private Long communityId;
    public Long getCommunityId() {
        return this.communityId;
    }
    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
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
    @Generated(hash = 1213034556)
    public CommentBean(Long id, String name, String avatar, String content,
            String date, Long communityId) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.content = content;
        this.date = date;
        this.communityId = communityId;
    }
    @Generated(hash = 373728077)
    public CommentBean() {
    }
}
