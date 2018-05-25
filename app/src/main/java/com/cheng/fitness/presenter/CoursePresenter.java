package com.cheng.fitness.presenter;

import com.cheng.baselib.mvpbase.baseImpl.BasePresenterImpl;
import com.cheng.fitness.contact.CourseContact;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.utils.GreenDaoUtil;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public class CoursePresenter extends BasePresenterImpl<CourseContact.view> implements CourseContact.presenter {

    public CoursePresenter(CourseContact.view view) {
        super(view);
    }

    @Override
    public void getCourseByCategory(String category) {
        List<CourseBean> courseBeans = GreenDaoUtil.getCoursesByCategory(category);
        if (courseBeans != null) {
            view.onGetCourseByCategorySuccess(courseBeans);
        } else {
            view.onGetCourseByCategoryFail("获取课程失败");
        }
    }
}
