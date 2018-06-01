package com.cheng.fitness.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.cheng.fitness.utils.greendao.gen.DaoSession;
import com.cheng.fitness.utils.greendao.gen.CommunityBeanDao;
import com.cheng.fitness.utils.greendao.gen.CommentBeanDao;

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
    //评论
    @ToMany(referencedJoinProperty = "communityId")
    private List<CommentBean> commentList;
    /** Used for active entity operations. */
    @Generated(hash = 1406028016)
    private transient CommunityBeanDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
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
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1195658147)
    public synchronized void resetCommentList() {
        commentList = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 480144550)
    public List<CommentBean> getCommentList() {
        if (commentList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CommentBeanDao targetDao = daoSession.getCommentBeanDao();
            List<CommentBean> commentListNew = targetDao._queryCommunityBean_CommentList(id);
            synchronized (this) {
                if(commentList == null) {
                    commentList = commentListNew;
                }
            }
        }
        return commentList;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1108212105)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCommunityBeanDao() : null;
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
