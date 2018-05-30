package com.cheng.fitness.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.baseImpl.BaseFragment;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.common.constant.InsertConstant;
import com.cheng.fitness.contact.TrainContact;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.model.UserBean;
import com.cheng.fitness.presenter.TrainPresenter;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.utils.inter.OnUpdateListener;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */

public class TrainFragment extends BaseFragment<TrainContact.presenter> implements TrainContact.view {

    @Bind(R.id.tvStartMake)
    TextView tvStartMake;
    @Bind(R.id.tvAddTrain)
    TextView tvAddTrain;
    @Bind(R.id.tvAddCourseLabel)
    TextView tvAddCourseLabel;
    @Bind(R.id.mineFitnessPlanLayout)
    LinearLayout mineFitnessPlanLayout;
    @Bind(R.id.mineTrainLayout)
    RelativeLayout mineTrainLayout;

    private OnUpdateListener mOnUpdateListener;

    @Override
    public TrainContact.presenter initPresenter() {
        return new TrainPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_train;
    }

    @Override
    public void loadData() {
        super.loadData();
        if (ConfigConstant.getKeyFirstTime()) {
            InsertConstant.insertCourses();
            InsertConstant.insertCommunities();
            UserBean bean = GreenDaoUtil.getUser(ConfigConstant.getKeyUserNickname());
            bean.setIsFirstTime(false);
            GreenDaoUtil.updateUser(bean);
            ConfigConstant.setKeyFirstTime(false);
        }
    }

    @Override
    public void reLoadData() {
        super.reLoadData();
        getMinePlanData();
    }

    private void getMinePlanData() {
        presenter.getMinePlan();
    }

    public void setUpdateListener(OnUpdateListener onUpdateListener) {
        mOnUpdateListener = onUpdateListener;
    }

    @OnClick({R.id.tvStartMake, R.id.tvAddTrain})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvStartMake:
                if (mOnUpdateListener != null) mOnUpdateListener.onUpdatePosition(2);
                break;
            case R.id.tvAddTrain:

                break;
        }
    }

    //获取我的健身计划成功
    @Override
    public void onGetMinePlanSuccess(List<CourseBean> beans) {
        //如果没有健身计划，则隐藏“我的健身计划”
        if (beans.size() == 0) mineTrainLayout.setVisibility(View.GONE);
        handleData(beans);
    }

    //获取我的健身计划失败
    @Override
    public void onGetMinePlanFail(String msg) {
        showToast(msg);
    }

    private void handleData(List<CourseBean> beans) {
        mineFitnessPlanLayout.removeAllViews();
        for (CourseBean bean : beans) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_mine_fitness_plan, null);
            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            TextView tvHasAppliance = (TextView) view.findViewById(R.id.tvHasAppliance);
            TextView tvTime = (TextView) view.findViewById(R.id.tvTime);
            TextView tvStrength = (TextView) view.findViewById(R.id.tvStrength);
            tvName.setText(bean.getName());
            tvHasAppliance.setText(bean.getHasAppliance() ? "有器械" : "无器械");
            tvTime.setText(String.format(getString(R.string.fitness_time), String.valueOf(bean.getTime())));
            tvStrength.setText(String.format(getString(R.string.fitness_strength), bean.getStrength()));
            mineFitnessPlanLayout.addView(view);
        }
    }
}
