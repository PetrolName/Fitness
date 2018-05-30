package com.cheng.fitness.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cheng.baselib.mvpbase.baseImpl.BaseFragment;
import com.cheng.baselib.utils.ScreenUtil;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.Constant;
import com.cheng.fitness.contact.CourseContact;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.model.CourseLeftBean;
import com.cheng.fitness.presenter.CoursePresenter;
import com.cheng.fitness.utils.RxRecyclerViewDividerTool;
import com.cheng.fitness.views.activity.CourseDetailActivity;
import com.cheng.fitness.views.adapter.CourseLeftAdapter;
import com.cheng.fitness.views.adapter.CourseRightAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */

public class CourseFragment extends BaseFragment<CourseContact.presenter> implements CourseContact.view {

    @Bind(R.id.rvLeft)
    RecyclerView rvLeft;
    @Bind(R.id.rvRight)
    RecyclerView rvRight;

    private CourseLeftAdapter mCourseLeftAdapter;
    private CourseRightAdapter mCourseRightAdapter;

    private List<CourseLeftBean> mLeftList = new ArrayList<>();
    private List<CourseBean> mRightList = new ArrayList<>();

    private String[] courseList = {"健身入门", "高效燃脂", "纤腿练习", "马甲线养成", "翘臀训练", "上肢练习", "短时训练", "突破极限", "综合能力"};

    private String[] strengthList = {"低", "中", "高"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    public CourseContact.presenter initPresenter() {
        return new CoursePresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initCourseLeft();
        mCourseLeftAdapter = new CourseLeftAdapter(mLeftList);
        mCourseRightAdapter = new CourseRightAdapter(mRightList);
        rvLeft.addItemDecoration(new RxRecyclerViewDividerTool(ScreenUtil.dp2px(getActivity(), 1f)));
        rvRight.addItemDecoration(new RxRecyclerViewDividerTool(ScreenUtil.dp2px(getActivity(), 8f)));
        rvLeft.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRight.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvLeft.setAdapter(mCourseLeftAdapter);
        rvRight.setAdapter(mCourseRightAdapter);
        mCourseLeftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                setLeftPosition(position);                          //设置左边选中的栏目
                mCourseLeftAdapter.notifyDataSetChanged();
                presenter.getCourseByCategory(courseList[position]);
            }
        });
        mCourseRightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), CourseDetailActivity.class).putExtra(Constant.KEY_COURSE, mRightList.get(position)));
            }
        });
    }

    @Override
    public void reLoadData() {
        super.reLoadData();
        presenter.getCourseByCategory(courseList[0]);
    }

    private void initCourseLeft() {
        for (int i = 0; i < courseList.length; i++) {
            CourseLeftBean bean = new CourseLeftBean();
            bean.setName(courseList[i]);
            bean.setFlag(i == 0 ? true : false);
            mLeftList.add(bean);
        }
    }

    private void initCourseRight() {
        mRightList.clear();
        for (int i = 0; i < 10; i++) {
            CourseBean bean = new CourseBean();
            bean.setStrength(getStrength());
            bean.setTime((int) (Math.random() * 25 + 5)); //时间设为[5,30)区间
            bean.setTvExpend((int) (Math.random() * 250 + 50)); //消耗设为[50, 300)区间
            mRightList.add(bean);
        }
    }

    private String getStrength() {
        return strengthList[(int) (Math.random() * 3)];
    }


    private void setLeftPosition(int position) {
        for (CourseLeftBean bean: mLeftList) {
            bean.setFlag(false);
        }
        mLeftList.get(position).setFlag(true);
    }

    //根据类别获取课程成功
    @Override
    public void onGetCourseByCategorySuccess(List<CourseBean> beans) {
        if (beans != null && beans.size() != 0) {
            mRightList.clear();
            mRightList.addAll(beans);
        } else {
            initCourseRight();
        }
        mCourseRightAdapter.notifyDataSetChanged();
        rvRight.smoothScrollToPosition(0);
    }

    //根据类别获取课程失败
    @Override
    public void onGetCourseByCategoryFail(String msg) {
        showToast(msg);
    }
}
