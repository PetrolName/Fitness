package com.cheng.fitness.views.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cheng.fitness.R;
import com.cheng.fitness.model.CourseBean;
import com.cheng.fitness.model.CourseLeftBean;
import com.cheng.fitness.model.CourseRightBean;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */

public class CourseRightAdapter extends BaseQuickAdapter<CourseBean, BaseViewHolder> {

    public CourseRightAdapter(@Nullable List<CourseBean> data) {
        super(R.layout.item_fragment_course_right, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseBean item) {
        helper.setText(R.id.tvTime, String.valueOf(item.getTime()));
        helper.setText(R.id.tvStrength, item.getStrength());
        helper.setText(R.id.tvExpend, String.valueOf(item.getTvExpend()));
        helper.setText(R.id.tvName, TextUtils.isEmpty(item.getName())? "":item.getName());
        helper.setText(R.id.tvHasAppliance, item.getHasAppliance()? "有器械":"无器械");
    }
}
