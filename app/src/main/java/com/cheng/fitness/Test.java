package com.cheng.fitness;

import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.utils.GreenDaoUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/22 0022
 * desc:
 */

public class Test {
    public static void main(String[] args) {
        String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date());
        System.out.println(str);
    }
}
