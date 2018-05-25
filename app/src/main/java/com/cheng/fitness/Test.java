package com.cheng.fitness;

import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.utils.GreenDaoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/22 0022
 * desc:
 */

public class Test {
    public static void main(String[] args) {
        List<CourseBean> beans = new ArrayList<>();
        CourseBean bean = new CourseBean();
        bean.setName("适应性腹部训练");
        bean.setCategory("健身入门");
        bean.setExercisePosition("腹部");
        bean.setHasAppliance(false);
        bean.setDetail("对腹肌进行全方位的唤醒刺激，强化腹肌线条的同时加核心力量。包括动作：抬腿卷腹，仰卧剪式抬腿，侧卧提髋，卷腹，平板支撑，登山。拉伸：腹部拉伸");
        bean.setStrength("低");
        bean.setTime(5);
        bean.setTvExpend(52);
        beans.add(bean);
        GreenDaoUtil.saveCourses(beans);

        List<CourseBean> courseBeens = GreenDaoUtil.getCoursesByCategory("健身入门");
        System.out.println("" + courseBeens.get(0).getDetail());
//        List<CourseLeftBean> lists = new ArrayList<>();
//        CourseLeftBean bean = new CourseLeftBean();
//        bean.setFlag(true);
//        bean.setName("aaa");
//        lists.add(bean);
//        System.out.println("---" + lists.get(0).isFlag());
//        lists.get(0).setFlag(false);
//        System.out.println("---" + lists.get(0).isFlag());
    }
}
