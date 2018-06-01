package com.cheng.fitness.presenter;

import com.cheng.baselib.mvpbase.baseImpl.BasePresenterImpl;
import com.cheng.fitness.contact.FitnessContact;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.utils.GreenDaoUtil;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public class FitnessPresenter extends BasePresenterImpl<FitnessContact.view> implements FitnessContact.presenter {

    public FitnessPresenter(FitnessContact.view view) {
        super(view);
    }

    @Override
    public void getMinePlan() {
        List<CourseBean> courseBeans = GreenDaoUtil.getMinePlanCourse(true);
        if (courseBeans != null) {
            view.onGetMinePlanSuccess(courseBeans);
        } else {
            view.onGetMinePlanFail("获取我的健身计划失败");
        }
    }
}
