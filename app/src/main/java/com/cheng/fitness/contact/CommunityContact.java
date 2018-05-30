package com.cheng.fitness.contact;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.BaseView;
import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.model.CourseBean;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public interface CommunityContact {
    interface view extends BaseView {
        void onGetCommunitiesSuccess(List<CommunityBean> beans);
        void onGetCommunitiesFail(String msg);
    }

    interface presenter extends BasePresenter {
        void getCommunities();
    }
}
