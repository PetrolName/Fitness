package com.cheng.fitness.views.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.model.FitnessRecordBean;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.utils.SystemUtil;
import com.cheng.fitness.views.widget.dialog.MLAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * author: PengCheng
 * time: 2018/5/30 0030
 * desc: 计时
 */

public class TimeActivity extends BaseActivity {

    @Bind(R.id.chronometer)
    Chronometer chronometer;
    @Bind(R.id.tvStartOrStop)
    TextView tvStartOrStop;

    private boolean isStart = false;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_time;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tvStartOrStop)
    public void onClick(View view) {
        if (!isStart) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            tvStartOrStop.setText("结束");
        } else {
            chronometer.stop();
            tvStartOrStop.setText("开始");
            addTirednessValue();
        }
        isStart = !isStart;
    }

    private void addTirednessValue() {
        List<String> items = new ArrayList<>();
        items.add("很轻松");
        items.add("一般般");
        items.add("很累");
        MLAlertDialog mlAlertDialog = new MLAlertDialog(TimeActivity.this, "选择疲惫值", items);
        mlAlertDialog.init(new MLAlertDialog.OnItemClickListner() {
            @Override
            public void onClick(int position) {
                String tireStr = null;
                switch (position) {
                    case 0:
                        tireStr = "很轻松";
                        break;
                    case 1:
                        tireStr = "一般般";
                        break;
                    case 2:
                        tireStr = "很累";
                        break;
                }
                FitnessRecordBean bean = new FitnessRecordBean();
                bean.setTire(tireStr);
                bean.setDistance("0.0");
                Timber.d("健身的总秒数：" + getChronometerSeconds(chronometer));
                bean.setDuration(getChronometerSeconds(chronometer));
                bean.setTime(SystemUtil.getDate());
                GreenDaoUtil.saveFitnessRecord(bean);
                showToast("记录成功");
                TimeActivity.this.finish();
            }
        });
    }

    //获取健身总秒数
    public  static String getChronometerSeconds(Chronometer cmt) {
        int totalss = 0;
        String string = cmt.getText().toString();
        if(string.length()==7){

            String[] split = string.split(":");
            String string2 = split[0];
            int hour = Integer.parseInt(string2);
            int Hours =hour*3600;
            String string3 = split[1];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[2]);
            totalss = Hours+Mins+SS;
            return String.valueOf(totalss);
        } else if(string.length()==5){

            String[] split = string.split(":");
            String string3 = split[0];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[1]);

            totalss =Mins+SS;
            return String.valueOf(totalss);
        }
        return String.valueOf(totalss);
    }
}
