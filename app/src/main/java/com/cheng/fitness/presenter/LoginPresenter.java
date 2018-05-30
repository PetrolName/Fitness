package com.cheng.fitness.presenter;

import android.text.TextUtils;

import com.cheng.baselib.mvpbase.baseImpl.BasePresenterImpl;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.contact.CourseContact;
import com.cheng.fitness.contact.LoginContact;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.model.UserBean;
import com.cheng.fitness.utils.GreenDaoUtil;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public class LoginPresenter extends BasePresenterImpl<LoginContact.view> implements LoginContact.presenter {

    public LoginPresenter(LoginContact.view view) {
        super(view);
    }


    @Override
    public void login(String nickname, String password) {
        UserBean bean = GreenDaoUtil.getUser(nickname);
        if (bean != null) {
            if (TextUtils.equals(password, bean.getPassword())) {
                ConfigConstant.setKeyUserNickname(nickname);
                ConfigConstant.setKeyUserPassword(password);
                ConfigConstant.setKeyUserGender(TextUtils.isEmpty(bean.getGender()) ? "" : bean.getGender());
                view.onLoginSuccess();
            } else {
                view.onLoginFail("密码不正确");
            }
        } else {
            view.onLoginFail("该用户不存在");
        }
    }
}
