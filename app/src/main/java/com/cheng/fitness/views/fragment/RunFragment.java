package com.cheng.fitness.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseFragment;
import com.cheng.fitness.R;
import com.cheng.fitness.model.FitnessRecordBean;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.views.activity.TimeActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: PengCheng
 * time: 2018/5/30 0030
 * desc:
 */

public class RunFragment extends BaseFragment {
    @Bind(R.id.tvDuration)
    TextView tvDuration;
    @Bind(R.id.tvDistance)
    TextView tvDistance;
    @Bind(R.id.tvRun)
    TextView tvRun;

    private int totalTime = 0;
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_run;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void reLoadData() {
        super.reLoadData();
        getData();
    }

    @OnClick(R.id.tvRun)
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), TimeActivity.class));
    }

    private void getData() {
        totalTime = 0;
        List<FitnessRecordBean> beans = GreenDaoUtil.getFitnessRecords();
        if (beans != null) {
            for (FitnessRecordBean bean: beans) {
                totalTime += Integer.parseInt(bean.getDuration());
            }
            tvDuration.setText(String.valueOf(totalTime));
        }
    }
}
