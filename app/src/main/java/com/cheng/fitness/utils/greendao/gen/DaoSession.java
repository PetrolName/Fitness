package com.cheng.fitness.utils.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.model.UserBean;

import com.cheng.fitness.utils.greendao.gen.CommunityBeanDao;
import com.cheng.fitness.utils.greendao.gen.CourseBeanDao;
import com.cheng.fitness.utils.greendao.gen.UserBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig communityBeanDaoConfig;
    private final DaoConfig courseBeanDaoConfig;
    private final DaoConfig userBeanDaoConfig;

    private final CommunityBeanDao communityBeanDao;
    private final CourseBeanDao courseBeanDao;
    private final UserBeanDao userBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        communityBeanDaoConfig = daoConfigMap.get(CommunityBeanDao.class).clone();
        communityBeanDaoConfig.initIdentityScope(type);

        courseBeanDaoConfig = daoConfigMap.get(CourseBeanDao.class).clone();
        courseBeanDaoConfig.initIdentityScope(type);

        userBeanDaoConfig = daoConfigMap.get(UserBeanDao.class).clone();
        userBeanDaoConfig.initIdentityScope(type);

        communityBeanDao = new CommunityBeanDao(communityBeanDaoConfig, this);
        courseBeanDao = new CourseBeanDao(courseBeanDaoConfig, this);
        userBeanDao = new UserBeanDao(userBeanDaoConfig, this);

        registerDao(CommunityBean.class, communityBeanDao);
        registerDao(CourseBean.class, courseBeanDao);
        registerDao(UserBean.class, userBeanDao);
    }
    
    public void clear() {
        communityBeanDaoConfig.getIdentityScope().clear();
        courseBeanDaoConfig.getIdentityScope().clear();
        userBeanDaoConfig.getIdentityScope().clear();
    }

    public CommunityBeanDao getCommunityBeanDao() {
        return communityBeanDao;
    }

    public CourseBeanDao getCourseBeanDao() {
        return courseBeanDao;
    }

    public UserBeanDao getUserBeanDao() {
        return userBeanDao;
    }

}
