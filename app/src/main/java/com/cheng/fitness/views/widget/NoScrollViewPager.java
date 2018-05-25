package com.cheng.fitness.views.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ZhangPengCheng
 */
public class NoScrollViewPager extends ViewPager {

    private boolean noScroll = false;
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (noScroll) return false;
        else return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll) return false;
        else return super.onInterceptTouchEvent(arg0);
    }
}