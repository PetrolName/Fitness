package com.cheng.fitness.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.Constant;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.views.widget.bottomsheet.BottomSheet;
import com.cheng.fitness.views.widget.bottomsheet.listener.OnMenuItemClickListener;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author: PengCheng
 * time: 2018/5/24 0024
 * desc: 课程详情
 */

public class CourseDetailActivity extends BaseActivity {

    @Bind(R.id.ivBack)
    ImageView ivBack;
    @Bind(R.id.ivJoinTraining)
    ImageView ivJoinTraining;
    @Bind(R.id.ivMore)
    ImageView ivMore;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvHasAppliance)
    TextView tvHasAppliance;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tvStrength)
    TextView tvStrength;
    @Bind(R.id.tvExpend)
    TextView tvExpend;
    @Bind(R.id.tvDetail)
    TextView tvDetail;

    private CourseBean mCourseBean;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_detail;
    }

    @Override
    public void handleIntent(Intent intent) {
        super.handleIntent(intent);
        mCourseBean = (CourseBean) getIntent().getSerializableExtra(Constant.KEY_COURSE);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (mCourseBean == null) return;
        tvName.setText(mCourseBean.getName());
        tvHasAppliance.setText(mCourseBean.getHasAppliance()? "有器械":"无器械");
        tvTime.setText(String.valueOf(mCourseBean.getTime()));
        tvStrength.setText(mCourseBean.getStrength());
        tvExpend.setText(String.valueOf(mCourseBean.getTvExpend()));
        tvDetail.setText(mCourseBean.getDetail());
        ivJoinTraining.setVisibility(mCourseBean.getHasAddPlan()?View.GONE:View.VISIBLE);
    }

    @OnClick({R.id.ivBack, R.id.ivJoinTraining, R.id.ivMore})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                CourseDetailActivity.this.finish();
                break;
            case R.id.ivJoinTraining:
                setAddCourse(true);
                break;

            case R.id.ivMore:
                final BottomSheet bottomSheet = BottomSheet.Builder
                        .newBuilder(this)
                        .setItem(new BottomSheet.MenuItem(mCourseBean.getHasAddPlan()?"退出课程":"参加课程", 0, "tag"))
                        .build();
                bottomSheet.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                    @Override
                    public void onItemClick(BottomSheet.MenuItem item) {
                        bottomSheet.dismiss();
                        setAddCourse(!mCourseBean.getHasAddPlan());
                    }
                });
                bottomSheet.show();
                break;
            default:
                break;
        }
    }

    private void setAddCourse(boolean isAdd) {
        mCourseBean.setHasAddPlan(isAdd);
        GreenDaoUtil.updateCourse(mCourseBean);
        ivJoinTraining.setVisibility(isAdd? View.GONE:View.VISIBLE);
        showToast(isAdd?"添加课程成功":"退出课程成功");
    }
}
