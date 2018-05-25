package com.cheng.fitness.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc: 课程
 */
@Entity
public class CourseBean implements Serializable {
    @Id(autoincrement = true)
    private Long id;
    //课程名字
    private String name;
    //是否有器械
    private boolean hasAppliance;
    //锻炼部位
    private String exercisePosition;
    //图片地址
    private String imageUrl;
    //时间
    private int time;
    //强度
    private String strength;
    //消耗
    private int tvExpend;
    //详情
    private String detail;
    //类别
    private String category;

    //是否添加到健身计划
    private boolean hasAddPlan;

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getTvExpend() {
        return this.tvExpend;
    }

    public void setTvExpend(int tvExpend) {
        this.tvExpend = tvExpend;
    }

    public String getStrength() {
        return this.strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getExercisePosition() {
        return this.exercisePosition;
    }

    public void setExercisePosition(String exercisePosition) {
        this.exercisePosition = exercisePosition;
    }

    public boolean getHasAppliance() {
        return this.hasAppliance;
    }

    public void setHasAppliance(boolean hasAppliance) {
        this.hasAppliance = hasAppliance;
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getHasAddPlan() {
        return this.hasAddPlan;
    }

    public void setHasAddPlan(boolean hasAddPlan) {
        this.hasAddPlan = hasAddPlan;
    }

    @Generated(hash = 153597777)
    public CourseBean(Long id, String name, boolean hasAppliance, String exercisePosition,
            String imageUrl, int time, String strength, int tvExpend, String detail,
            String category, boolean hasAddPlan) {
        this.id = id;
        this.name = name;
        this.hasAppliance = hasAppliance;
        this.exercisePosition = exercisePosition;
        this.imageUrl = imageUrl;
        this.time = time;
        this.strength = strength;
        this.tvExpend = tvExpend;
        this.detail = detail;
        this.category = category;
        this.hasAddPlan = hasAddPlan;
    }

    @Generated(hash = 858107730)
    public CourseBean() {
    }

}
