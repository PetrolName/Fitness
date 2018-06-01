package com.cheng.fitness.views.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.model.FitnessRecordBean;
import com.cheng.fitness.utils.GreenDaoUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: PengCheng
 * time: 2018/5/31 0031
 * desc: 跑步记录
 */

public class RunRecordActivity extends BaseActivity {

    @Bind(R.id.tvDuration)
    TextView tvDuration;
    @Bind(R.id.tvDistance)
    TextView tvDistance;
    @Bind(R.id.runRecordLayout)
    LinearLayout runRecordLayout;

    private int totalTime = 0;
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_run_record;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
        toolBar.setTitleText("跑步记录");
    }

    @Override
    public void reLoadData() {
        super.reLoadData();
        getData();
    }

    private void getData() {
        totalTime = 0;
        List<FitnessRecordBean> beans = GreenDaoUtil.getFitnessRecords();
        if (beans != null) {
            runRecordLayout.removeAllViews();
            for (FitnessRecordBean bean: beans) {
                totalTime += Integer.parseInt(bean.getDuration());
                View view = LayoutInflater.from(RunRecordActivity.this).inflate(R.layout.item_run_record, null);
                TextView duration = (TextView) view.findViewById(R.id.tvDuration);
                TextView distance = (TextView) view.findViewById(R.id.tvDistance);
                TextView tire = (TextView) view.findViewById(R.id.tvTire);
                TextView date = (TextView) view.findViewById(R.id.tvDate);
                duration.setText(String.format(getString(R.string.run_duration), bean.getDuration()));
                distance.setText(String.format(getString(R.string.run_distance), bean.getDistance()));
                tire.setText(String.format(getString(R.string.run_tire), bean.getTire()));
                date.setText(bean.getTime());
                runRecordLayout.addView(view);
            }
            tvDuration.setText(String.valueOf(totalTime));
        }
    }

}
