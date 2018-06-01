package com.cheng.fitness.views.fragment;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.baseImpl.BaseFragment;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.common.constant.Constant;
import com.cheng.fitness.common.constant.InsertConstant;
import com.cheng.fitness.contact.FitnessContact;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.model.UserBean;
import com.cheng.fitness.presenter.FitnessPresenter;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.utils.inter.OnUpdateListener;
import com.cheng.fitness.views.activity.CourseDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc: 健身
 */

public class FitnessFragment extends BaseFragment<FitnessContact.presenter> implements FitnessContact.view {

    @Bind(R.id.tvStartMake)
    TextView tvStartMake;
    @Bind(R.id.tvSetAlarmClock)
    TextView tvSetAlarmClock;
    @Bind(R.id.tvAddCourseLabel)
    TextView tvAddCourseLabel;
    @Bind(R.id.mineFitnessPlanLayout)
    LinearLayout mineFitnessPlanLayout;
    @Bind(R.id.mineTrainLayout)
    RelativeLayout mineTrainLayout;

    private OnUpdateListener mOnUpdateListener;

    @Override
    public FitnessContact.presenter initPresenter() {
        return new FitnessPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fitness;
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
        Timber.d("执行我的健身计划");
        presenter.getMinePlan();
    }

    public void setUpdateListener(OnUpdateListener onUpdateListener) {
        mOnUpdateListener = onUpdateListener;
    }

    @OnClick({R.id.tvStartMake, R.id.tvSetAlarmClock})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvStartMake:
                if (mOnUpdateListener != null) mOnUpdateListener.onUpdatePosition(2);
                break;
            case R.id.tvSetAlarmClock:
                presenter.startAlarmClock(getActivity());
                break;
        }
    }

    //获取我的健身计划成功
    @Override
    public void onGetMinePlanSuccess(List<CourseBean> beans) {
        handleData(beans);
    }

    //获取我的健身计划失败
    @Override
    public void onGetMinePlanFail(String msg) {
        showToast(msg);
    }

    //启动闹钟成功
    @Override
    public void onStartAlarmClockSuccess(Intent intent) {
        if (intent != null) {
            getActivity().startActivity(intent);
        } else {
            Intent intent1 = new Intent(Settings.ACTION_DATE_SETTINGS);
            getActivity().startActivity(intent1);
        }
    }

    //启动闹钟失败
    @Override
    public void onStartAlarmClockFail(String msg) {

    }

    private void handleData(final List<CourseBean> beans) {
        mineFitnessPlanLayout.removeAllViews();
        if (beans.size() == 0) tvAddCourseLabel.setVisibility(View.VISIBLE);
        else tvAddCourseLabel.setVisibility(View.GONE);
        for (final CourseBean bean : beans) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_mine_fitness_plan, null);
            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            TextView tvHasAppliance = (TextView) view.findViewById(R.id.tvHasAppliance);
            TextView tvTime = (TextView) view.findViewById(R.id.tvTime);
            TextView tvStrength = (TextView) view.findViewById(R.id.tvStrength);
            tvName.setText(bean.getName());
            tvHasAppliance.setText(bean.getHasAppliance() ? "有器械" : "无器械");
            tvTime.setText(String.format(getString(R.string.fitness_time), String.valueOf(bean.getTime())));
            tvStrength.setText(String.format(getString(R.string.fitness_strength), bean.getStrength()));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), CourseDetailActivity.class).putExtra(Constant.KEY_COURSE, bean));
                }
            });
            mineFitnessPlanLayout.addView(view);
        }
    }
}
