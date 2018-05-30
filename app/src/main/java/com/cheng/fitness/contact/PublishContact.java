package com.cheng.fitness.contact;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.BaseView;
import com.cheng.fitness.model.CommunityBean;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public interface PublishContact {
    interface view extends BaseView {
        void onPublishSuccess();
        void onPublishFail(String msg);
    }

    interface presenter extends BasePresenter {
        void publish(String content);
    }
}
