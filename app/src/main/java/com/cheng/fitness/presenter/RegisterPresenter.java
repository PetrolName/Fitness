package com.cheng.fitness.presenter;

import android.text.TextUtils;

import com.cheng.baselib.mvpbase.baseImpl.BasePresenterImpl;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.contact.RegisterContact;
import com.cheng.fitness.model.UserBean;
import com.cheng.fitness.utils.GreenDaoUtil;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContact.view> implements RegisterContact.presenter {

    public RegisterPresenter(RegisterContact.view view) {
        super(view);
    }

    @Override
    public void register(String nickname, String password) {
        UserBean bean = GreenDaoUtil.getUser(nickname);
        if (bean != null) {
            view.onRegisterFail("该用户已存在");
        } else {
            UserBean userBean = new UserBean();
            userBean.setNickname(nickname);
            userBean.setPassword(password);
            userBean.setIsFirstTime(true);
            GreenDaoUtil.saveUser(userBean);
            ConfigConstant.setKeyUserNickname(nickname);
            ConfigConstant.setKeyUserPassword(password);
            view.onRegisterSuccess();
        }
    }

}
