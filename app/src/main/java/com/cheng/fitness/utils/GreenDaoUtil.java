package com.cheng.fitness.utils;

import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.utils.greendao.DaoManager;
import com.cheng.fitness.utils.greendao.gen.CourseBeanDao;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public class GreenDaoUtil {

    //插入课程到数据库，如果存在，则替换
    public static void saveCourses(List<CourseBean> courseBeens) {
        CourseBeanDao dao = DaoManager.getInstance().getDaoSession().getCourseBeanDao();
        dao.insertOrReplaceInTx(courseBeens);
    }

    //插入课程到数据库，如果存在，则替换
    public static void updateCourse(CourseBean courseBeen) {
        CourseBeanDao dao = DaoManager.getInstance().getDaoSession().getCourseBeanDao();
        dao.update(courseBeen);
    }

    //根据类别来查找课程
    public static List<CourseBean> getCoursesByCategory(String category) {
        CourseBeanDao dao = DaoManager.getInstance().getDaoSession().getCourseBeanDao();
        List<CourseBean> list = dao.queryBuilder().where(CourseBeanDao.Properties.Category.eq(category)).build().list();
        return list;
    }

    //根据类别来查找课程
    public static List<CourseBean> getMinePlanCourse(boolean hasAddPlan) {
        CourseBeanDao dao = DaoManager.getInstance().getDaoSession().getCourseBeanDao();
        List<CourseBean> list = dao.queryBuilder().where(CourseBeanDao.Properties.HasAddPlan.eq(true)).build().list();
        return list;
    }
}
