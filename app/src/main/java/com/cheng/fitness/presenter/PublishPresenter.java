package com.cheng.fitness.presenter;

import android.text.TextUtils;

import com.cheng.baselib.mvpbase.baseImpl.BasePresenterImpl;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.contact.CommunityContact;
import com.cheng.fitness.contact.PublishContact;
import com.cheng.fitness.model.CommunityBean;
import com.cheng.fitness.model.UserBean;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.utils.SystemUtil;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/23 0023
 * desc:
 */

public class PublishPresenter extends BasePresenterImpl<PublishContact.view> implements PublishContact.presenter {

    public PublishPresenter(PublishContact.view view) {
        super(view);
    }


    @Override
    public void publish(String content, String imageUrl) {
        try {
            UserBean userBean = GreenDaoUtil.getUser(ConfigConstant.getKeyUserNickname());
            CommunityBean bean = new CommunityBean();
            bean.setName(ConfigConstant.getKeyUserNickname());
            bean.setContent(content);
            bean.setTime(SystemUtil.getDate());
            bean.setComment(0);
            bean.setLike(0);
            if (userBean != null && !TextUtils.isEmpty(userBean.getAvatar())) bean.setAvatar(userBean.getAvatar());
            if (!TextUtils.isEmpty(imageUrl)) bean.setImage(imageUrl);
            GreenDaoUtil.saveCommunity(bean);
            view.onPublishSuccess();
        } catch (Exception e) {
            view.onPublishFail("发布失败");
        }
    }
}
