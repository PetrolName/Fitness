package com.cheng.fitness.contact;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.BaseView;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public interface RegisterContact {
    interface view extends BaseView {
        void onRegisterSuccess();
        void onRegisterFail(String msg);
    }

    interface presenter extends BasePresenter {
        void register(String nickname, String password);
    }
}
