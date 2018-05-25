package com.cheng.fitness.model;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */

public class CourseLeftBean {
    private String name;
    private boolean flag; //判断是否被选中，true为选中，false为未选中

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
