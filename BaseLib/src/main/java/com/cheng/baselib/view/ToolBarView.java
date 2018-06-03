package com.cheng.baselib.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cheng.baselib.R;
import com.cheng.baselib.utils.CompatUtil;
import com.cheng.baselib.utils.TextViewUtil;

/**
 * Created by ZhangPengCheng.
 */
@SuppressWarnings("unused")
public class ToolBarView extends LinearLayout {

    private TextView mTvTitle;
    private TextView mTvBack;
    private TextView mTvRight;

    public static final int TEXT_LEFT = 0;
    public static final int TEXT_TITLE = 1;
    public static final int TEXT_RIGHT = 2;


    public static final int MODEL_TITLE_GRAVITY_CENTER = 10;
    public static final int MODEL_TITLE_GRAVITY_LEFT = 11;

    private String leftText;
    private String titleText;
    private String rightText;

    private int leftColor;
    private int titleColor;
    private int rightColor;

    private float leftTextSize;
    private float titleTextSize;
    private float rightTextSize;


    public ToolBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        showStyle(context, attrs);
    }

    public ToolBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        showStyle(context, attrs);
    }

    private void showStyle(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray style = context.obtainStyledAttributes(attrs, R.styleable.ToolBarView);
            leftText = style.getString(R.styleable.ToolBarView_textLeft);
            titleText = style.getString(R.styleable.ToolBarView_textTitle);
            rightText = style.getString(R.styleable.ToolBarView_textRight);


            leftColor = style.getColor(R.styleable.ToolBarView_textColorLeft, Color.parseColor("#3d4246"));
            titleColor = style.getColor(R.styleable.ToolBarView_textColorTitle, Color.parseColor("#ffffff"));
            rightColor = style.getColor(R.styleable.ToolBarView_textColorRight, Color.parseColor("#ffffff"));

            leftTextSize = style.getDimension(R.styleable.ToolBarView_textSizeLeft, 16);
            titleTextSize = style.getDimension(R.styleable.ToolBarView_textSizeTitle, 16);
            rightTextSize = style.getDimension(R.styleable.ToolBarView_textSizeRight, 15);

            style.recycle();
        }
    }

    private void setStyle(TextView textView, String text, int color, float size) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
        textView.setTextColor(color);
        textView.setTextSize(size);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        LayoutInflater.from(getContext()).inflate(R.layout.view_toolbar, this, true);
        mTvBack = (TextView) findViewById(R.id.tv_bar_back);
        mTvTitle = (TextView) findViewById(R.id.tv_bar_title);
        mTvRight = (TextView) findViewById(R.id.tv_bar_right);


//        setBackgroundColor(CompatUtil.getColor(getContext(), R.color.cEC6564));
//
//        setStyle(mTvBack, leftText, leftColor, leftTextSize);
//        setStyle(mTvTitle, titleText, titleColor, titleTextSize);
//        setStyle(mTvRight, rightText, rightColor, rightTextSize);
    }

    public void setBackground(int color) {
        setBackgroundColor(CompatUtil.getColor(getContext(), color));
    }

    /**
     * 返回按钮,默认图片为R.mipmap.back
     *
     * @param activity 当前activity
     */
    public void setBackPressed(final Activity activity) {
        setBackPressed(activity, R.mipmap.icon_back);
    }

    public TextView getTvRight() {
        return mTvRight;
    }

    /**
     * 返回按钮
     *
     * @param activity 当前activity
     * @param resId    返回按钮图片
     */
    public void setBackPressed(final Activity activity, @DrawableRes int resId) {
        setBackImage(resId);
        mTvBack.setOnClickListener(v -> activity.onBackPressed());
    }


    /**
     * 设置返回图片
     */
    public void setBackImage(@DrawableRes int resId) {
        setDrawable(TEXT_LEFT, resId);
    }


    /**
     * 设置中间标题,默认居中
     */
    public void setTitleText(String text) {
        setTitleText(text, MODEL_TITLE_GRAVITY_CENTER);
    }

    /**
     * 设置中间标题
     */
    public void setTitleText(String text, int gravityType) {
        setText(TEXT_TITLE, text);
        setTitleGravity(gravityType);
    }

    /**
     * 设置右边标题
     */
    public void setRightText(CharSequence text) {
        setText(TEXT_RIGHT, text);
    }

    /**
     * 设置title显示格式,居中(默认)/左边对齐
     *
     * @param gravityType {@link ToolBarView#MODEL_TITLE_GRAVITY_CENTER} || {@link ToolBarView#MODEL_TITLE_GRAVITY_LEFT}
     */
    public void setTitleGravity(int gravityType) {
        switch (gravityType) {
            case MODEL_TITLE_GRAVITY_CENTER:
                mTvTitle.setGravity(Gravity.CENTER);
                break;
            case MODEL_TITLE_GRAVITY_LEFT:
                mTvTitle.setGravity(Gravity.CENTER_VERTICAL);
                break;
        }
    }

    /**
     * 设置文字
     *
     * @param textType 标题位置 详情定义为{@link ToolBarView#TEXT_LEFT} || {@link ToolBarView#TEXT_TITLE}|| {@link ToolBarView#TEXT_RIGHT}
     */
    public void setText(int textType, CharSequence text) {
        switch (textType) {
            case TEXT_LEFT:
                mTvBack.setText(text);
                break;
            case TEXT_TITLE:
                mTvTitle.setText(text);
                break;
            case TEXT_RIGHT:
                mTvRight.setText(text);
                break;
        }

    }

    /**
     * 设置字体大小
     *
     * @param textType 标题位置 详情定义为{@link ToolBarView#TEXT_LEFT} || {@link ToolBarView#TEXT_TITLE}|| {@link ToolBarView#TEXT_RIGHT}
     */
    public void setTextSize(int textType, float size) {
        switch (textType) {
            case TEXT_LEFT:
                mTvBack.setTextSize(size);
                break;
            case TEXT_TITLE:
                mTvTitle.setTextSize(size);
                break;
            case TEXT_RIGHT:
                mTvRight.setTextSize(size);
                break;
        }
    }

    /**
     * 设置字体颜色
     *
     * @param textType 标题位置 详情定义为{@link ToolBarView#TEXT_LEFT} || {@link ToolBarView#TEXT_TITLE}|| {@link ToolBarView#TEXT_RIGHT}
     * @param color    颜色id
     */
    public void setTextColor(int textType, int color) {
        switch (textType) {
            case TEXT_LEFT:
                mTvBack.setTextColor(color);
                break;
            case TEXT_TITLE:
                mTvTitle.setTextColor(color);
                break;
            case TEXT_RIGHT:
                mTvRight.setTextColor(color);
                break;
        }
    }


    /**
     * 设置标题图片
     * 左边标题默认在左边，中间标题默认在左边，右边标题默认在右边
     *
     * @param textType 标题位置 详情定义为{@link ToolBarView#TEXT_LEFT} || {@link ToolBarView#TEXT_TITLE}|| {@link ToolBarView#TEXT_RIGHT}
     * @param resId    图片资源id
     */
    public void setDrawable(int textType, @DrawableRes int resId) {
        switch (textType) {
            case TEXT_LEFT:
                TextViewUtil.setDrawable(mTvBack, resId, TextViewUtil.ORIENTATION_LEFT);
                break;
            case TEXT_TITLE:
                TextViewUtil.setDrawable(mTvTitle, resId, TextViewUtil.ORIENTATION_LEFT);
                break;
            case TEXT_RIGHT:
                TextViewUtil.setDrawable(mTvRight, resId, TextViewUtil.ORIENTATION_RIGHT);
                break;
        }
    }

    /**
     * 设置右边按钮是否可见
     */
    public void setRightIcoVisible(boolean visible) {
        if (visible) {
            mTvRight.setVisibility(VISIBLE);
        } else {
            mTvRight.setVisibility(GONE);
        }
    }

    public TextView getTvBack() {
        return mTvBack;
    }


    /**
     * 左title点击事件
     */
    public void setOnLeftClickListener(final OnBarLeftClickListener listener) {
        mTvBack.setOnClickListener(listener::onLeftClick);
    }

    /**
     * 右title点击事件
     */
    public void setOnRightClickListener(final OnBarRightClickListener listener) {
        mTvRight.setOnClickListener(listener::onRightClick);
    }

    public interface OnBarLeftClickListener {
        void onLeftClick(View v);
    }

    public interface OnBarRightClickListener {
        void onRightClick(View v);
    }


}
