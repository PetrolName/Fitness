package com.cheng.fitness.presenter;

import com.cheng.baselib.mvpbase.baseImpl.BasePresenterImpl;
import com.cheng.fitness.contact.CommunityContact;
import com.cheng.fitness.contact.CourseContact;
import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.utils.GreenDaoUtil;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public class CommunityPresenter extends BasePresenterImpl<CommunityContact.view> implements CommunityContact.presenter {

    public CommunityPresenter(CommunityContact.view view) {
        super(view);
    }

    @Override
    public void getCommunities() {
        List<CommunityBean> beans = GreenDaoUtil.getCommunities();
        if (beans != null) {
            view.onGetCommunitiesSuccess(beans);
        } else {
            view.onGetCommunitiesFail("获取数据失败");
        }
    }

}
