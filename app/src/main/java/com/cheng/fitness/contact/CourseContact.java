package com.cheng.fitness.contact;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.BaseView;
import com.cheng.fitness.model.CourseBean;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public interface CourseContact {
    interface view extends BaseView {
        void onGetCourseByCategorySuccess(List<CourseBean> beans);
        void onGetCourseByCategoryFail(String msg);
    }

    interface presenter extends BasePresenter {
        void getCourseByCategory(String category);
    }
}
