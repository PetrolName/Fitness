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

public interface TrainContact {
    interface view extends BaseView {
        void onGetMinePlanSuccess(List<CourseBean> beans);
        void onGetMinePlanFail(String msg);
    }

    interface presenter extends BasePresenter {
        void getMinePlan(String category);
    }
}
