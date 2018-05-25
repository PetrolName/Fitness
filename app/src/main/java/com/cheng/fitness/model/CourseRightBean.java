package com.cheng.fitness.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */
public class CourseRightBean {
    private String imageUrl;
    private int time;
    private String strength;
    private int tvExpend;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public int getTvExpend() {
        return tvExpend;
    }

    public void setTvExpend(int tvExpend) {
        this.tvExpend = tvExpend;
    }
}
