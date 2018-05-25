package com.cheng.fitness.views.adapter;

import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cheng.fitness.R;
import com.cheng.fitness.model.CourseLeftBean;

import java.util.List;

/**
 * author: PengCheng
 * time: 2018/5/21 0021
 * desc:
 */

public class CourseLeftAdapter extends BaseQuickAdapter<CourseLeftBean, BaseViewHolder> {

    public CourseLeftAdapter(@Nullable List<CourseLeftBean> data) {
        super(R.layout.item_fragment_course_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseLeftBean item) {
        helper.setText(R.id.name, item.getName());
        handleChoose((TextView) helper.getView(R.id.name), (ImageView) helper.getView(R.id.ivChoose), item);
    }

    private void handleChoose(TextView tvName, ImageView image, CourseLeftBean item) {
        if (item.isFlag()) {
            tvName.setBackgroundResource(R.color.white);
            tvName.setTextColor(tvName.getContext().getResources().getColor(R.color.c1f1f1f));
            tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            image.setVisibility(View.VISIBLE);
        } else {
            tvName.setBackgroundResource(R.color.cf6f5f5);
            tvName.setTextColor(tvName.getContext().getResources().getColor(R.color.c666666));
            tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            image.setVisibility(View.GONE);
        }
    }

}
