package com.cheng.fitness.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: PengCheng
 * time: 2018/5/30 0030
 * desc: 健身记录
 */
@Entity
public class FitnessRecordBean {
    @Id(autoincrement = true)
    private Long id;
    //锻炼持续时间
    private String duration;
    //锻炼的时间
    private String time;
    //锻炼的距离
    private String distance;
    //疲惫值
    private String tire;
    public String getDistance() {
        return this.distance;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDuration() {
        return this.duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTire() {
        return this.tire;
    }
    public void setTire(String tire) {
        this.tire = tire;
    }
    @Generated(hash = 1065543326)
    public FitnessRecordBean(Long id, String duration, String time, String distance,
            String tire) {
        this.id = id;
        this.duration = duration;
        this.time = time;
        this.distance = distance;
        this.tire = tire;
    }
    @Generated(hash = 698364819)
    public FitnessRecordBean() {
    }
}
