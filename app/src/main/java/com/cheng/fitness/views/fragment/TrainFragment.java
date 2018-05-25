package com.cheng.fitness.views.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseFragment;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.InsertConstant;
import com.cheng.fitness.contact.TrainContact;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.presenter.TrainPresenter;
import com.cheng.fitness.utils.inter.OnUpdateListener;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */

public class TrainFragment extends BaseFragment<TrainContact.presenter> implements TrainContact.view{

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
        InsertConstant.insert();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_train;
    }

    @Override
    public void reLoadData() {
        super.reLoadData();
        getMinePlanData();
    }

    private void getMinePlanData() {

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
        if (beans.size() == 0) return;
        handleData(beans);
    }

    //获取我的健身计划失败
    @Override
    public void onGetMinePlanFail(String msg) {

    }

    private void handleData(List<CourseBean> beans) {
        mineTrainLayout.removeAllViews();
        for (CourseBean bean:beans) {

        }
    }
}
