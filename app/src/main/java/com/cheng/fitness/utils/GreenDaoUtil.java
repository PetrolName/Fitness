package com.cheng.fitness.utils;

import com.cheng.fitness.model.CommentBean;
import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.model.FitnessRecordBean;
import com.cheng.fitness.model.UserBean;
import com.cheng.fitness.utils.greendao.DaoManager;
import com.cheng.fitness.utils.greendao.gen.CommentBeanDao;
import com.cheng.fitness.utils.greendao.gen.CommunityBeanDao;
import com.cheng.fitness.utils.greendao.gen.CourseBeanDao;
import com.cheng.fitness.utils.greendao.gen.FitnessRecordBeanDao;
import com.cheng.fitness.utils.greendao.gen.UserBeanDao;

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

    //保存用户信息
    public static void saveUser(UserBean bean) {
        UserBeanDao dao = DaoManager.getInstance().getDaoSession().getUserBeanDao();
        dao.insertOrReplace(bean);
    }

    //更新用户信息
    public static void updateUser(UserBean bean) {
        UserBeanDao dao = DaoManager.getInstance().getDaoSession().getUserBeanDao();
        dao.update(bean);
    }

    //查找用户信息
    public static UserBean getUser(String nickname) {
        UserBeanDao dao = DaoManager.getInstance().getDaoSession().getUserBeanDao();
        UserBean bean = dao.queryBuilder().where(UserBeanDao.Properties.Nickname.eq(nickname)).build().unique();
        return bean;
    }

    //保存社区信息
    public static void saveCommunity(CommunityBean bean) {
        CommunityBeanDao dao = DaoManager.getInstance().getDaoSession().getCommunityBeanDao();
        dao.insertOrReplace(bean);
    }

    //保存多个社区信息
    public static void saveCommunities(List<CommunityBean> beans) {
        CommunityBeanDao dao = DaoManager.getInstance().getDaoSession().getCommunityBeanDao();
        dao.insertOrReplaceInTx(beans);
    }

    //获取社区信息
    public static List<CommunityBean> getCommunities() {
        CommunityBeanDao dao = DaoManager.getInstance().getDaoSession().getCommunityBeanDao();
        return dao.queryBuilder().list();
    }

    //查找用户信息
    public static List<CommunityBean> getCommunitiesByName(String nickname) {
        CommunityBeanDao dao = DaoManager.getInstance().getDaoSession().getCommunityBeanDao();
        List<CommunityBean> beans = dao.queryBuilder().where(CommunityBeanDao.Properties.Name.eq(nickname)).build().list();
        return beans;
    }

    //更新社区信息
    public static void updateCommunity(CommunityBean bean) {
        CommunityBeanDao dao = DaoManager.getInstance().getDaoSession().getCommunityBeanDao();
        dao.update(bean);
    }

    //删除社区信息
    public static void deleteCommunity(Long communityId) {
        CommunityBeanDao dao = DaoManager.getInstance().getDaoSession().getCommunityBeanDao();
        dao.deleteByKey(communityId);
    }

    //保存健身记录
    public static void saveFitnessRecord(FitnessRecordBean bean) {
        FitnessRecordBeanDao dao = DaoManager.getInstance().getDaoSession().getFitnessRecordBeanDao();
        dao.insertOrReplace(bean);
    }

    //获取健身记录列表
    public static List<FitnessRecordBean> getFitnessRecords() {
        FitnessRecordBeanDao dao = DaoManager.getInstance().getDaoSession().getFitnessRecordBeanDao();
        return dao.queryBuilder().list();
    }

    //保存多个评论
    public static void saveComment(CommentBean bean) {
        CommentBeanDao dao = DaoManager.getInstance().getDaoSession().getCommentBeanDao();
        dao.insertOrReplace(bean);
    }
    //保存多个评论
    public static void saveComments(List<CommentBean> beans) {
        CommentBeanDao dao = DaoManager.getInstance().getDaoSession().getCommentBeanDao();
        dao.insertOrReplaceInTx(beans);
    }
    //获取评论
    public static List<CommentBean> getComments() {
        CommentBeanDao dao = DaoManager.getInstance().getDaoSession().getCommentBeanDao();
        return dao.queryBuilder().list();
    }

    //获取评论
    public static List<CommentBean> getComments(Long communityId) {
        CommentBeanDao dao = DaoManager.getInstance().getDaoSession().getCommentBeanDao();
        return dao.queryBuilder().where(CommentBeanDao.Properties.CommunityId.eq(communityId)).list();
    }

    //获取评论
    public static void deteleComment(Long communityId) {
        CommentBeanDao dao = DaoManager.getInstance().getDaoSession().getCommentBeanDao();
        dao.deleteByKey(communityId);
    }
}
