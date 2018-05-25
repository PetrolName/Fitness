package com.cheng.fitness.views.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheng.fitness.R;
import com.cheng.fitness.utils.DensityUtil;

import java.util.List;

public class BottomTabView extends LinearLayout {


    public int getLastPosition() {
        return lastPosition;
    }

    /**
     * 记录最新的选择位置
     */
    private int lastPosition = -1;

    /**
     * 所有 TabItem 的集合
     */
//    private List<View> tabItemViews;
    private List<TabItemView> tabItemViews;

    public BottomTabView(Context context) {
        super(context);
    }

    public BottomTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public BottomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置 Tab Item View
     */
//    public void setTabItemViews(List<View> tabItemViews) {
//        setTabItemViews(tabItemViews, null);
//    }
    public void setTabItemViews(List<TabItemView> tabItemViews) {
        setTabItemViews(tabItemViews, null);
    }

    /**
     * 设置 Tab Item View
     */
    public void setTabItemViews(List<TabItemView> tabItemViews, View centerView) {
        if (this.tabItemViews != null) {
            throw new RuntimeException("不能重复设置！");
        }

        if (tabItemViews == null || tabItemViews.size() < 2) {
            throw new RuntimeException("TabItemView 的数量必须大于2！");
        }

        this.tabItemViews = tabItemViews;
        for (int i = 0; i < tabItemViews.size(); i++) {

            if (centerView != null && i == tabItemViews.size() / 2) {
                this.addView(centerView);
            }

//            final View tabItemView = tabItemViews.get(i);
            final TabItemView tabItemView = tabItemViews.get(i);

            this.addView(tabItemView);

            final int finalI = i;

            tabItemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEvent(finalI);
//                    if (finalI == lastPosition) {
//                        // 第二次点击
//                        if (onSecondSelectListener != null) {
//                            onSecondSelectListener.onSecondSelect(finalI);
//                        }
//                        return;
//                    }
//
//                    updatePosition(finalI);
                }
            });
//            if (tabItemView.isCenter) {
//                tabItemView.ivIcon.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Timber.d("点击充值图片");
//                        onClickEvent(finalI);
//                    }
//                });
//            }
        }

        /**
         * 将所有的 TabItem 设置为 初始化状态
         */
//        for (View tab : tabItemViews) {
//            if (tab instanceof TabItemView) {
//                ((TabItemView)tab).setStatus(TabItemView.DEFAULT);
//            }
//        }
        for (TabItemView tab : tabItemViews) {
            tab.setStatus(TabItemView.DEFAULT);
        }

        /**
         * 默认状态选择第一个
         */
        updatePosition(0);
    }

    public void onClickEvent(int finalI) {
        if (finalI == lastPosition) {
            // 第二次点击
            if (onSecondSelectListener != null) {
                onSecondSelectListener.onSecondSelect(finalI);
            }
            return;
        }

        updatePosition(finalI);
    }
    /**
     * 更新被选中 Tab Item 的状态
     * 恢复上一个 Tab Item 的状态
     */
    public void updatePosition(int position) {
        if (lastPosition != position) {
            tabItemViews.get(position).setStatus(TabItemView.PRESS);
            if (lastPosition != -1) {
                tabItemViews.get(lastPosition).setStatus(TabItemView.DEFAULT);
            }
            lastPosition = position;
        }
        if (onTabItemSelectListener != null) {
            onTabItemSelectListener.onTabItemSelect(position);
        }
    }

    OnTabItemSelectListener onTabItemSelectListener;
    OnSecondSelectListener onSecondSelectListener;

    public void setOnTabItemSelectListener(OnTabItemSelectListener onTabItemSelectListener) {
        this.onTabItemSelectListener = onTabItemSelectListener;
    }

    public void setOnSecondSelectListener(OnSecondSelectListener onSecondSelectListener) {
        this.onSecondSelectListener = onSecondSelectListener;
    }

    /**
     * 第二次被选择的监听器
     */
    public interface OnSecondSelectListener {
        void onSecondSelect(int position);
    }

    /**
     * 第一次被选择的监听器
     */
    public interface OnTabItemSelectListener {
        void onTabItemSelect(int position);
    }

    /**
     * Item
     */
    public static class TabItemView extends LinearLayout {

        /**
         * 两个状态 选中、未选中
         */
        public final static int PRESS = 1;
        public final static int DEFAULT = 2;

        /**
         * Item 的标题
         */
        public String title;

        /**
         * 标题的两个状态的颜色 选中、未选中
         */
        public int colorDef;
        public int colorPress;

        /**
         * 两个图标的 资源 id ，选中、未选中
         */
        public int iconResDef;
        public int iconResPress;

        public RelativeLayout viewTabView;
        public TextView tvTitle;
        public ImageView ivIcon;

        public boolean isCenter;

        private Context mContext;

        public TabItemView(Context context, String title, int colorDef, int colorPress,
                           int iconResDef, int iconResPress, boolean isCenter) {
            super(context);
            mContext = context;
            this.title = title;
            this.colorDef = colorDef;
            this.colorPress = colorPress;
            this.iconResDef = iconResDef;
            this.iconResPress = iconResPress;
            this.isCenter = isCenter;
            init();
        }

        /**
         * 初始化
         */
        public void init() {
            View view = LayoutInflater.from(super.getContext()).inflate(R.layout.view_tab_item, this);
            viewTabView = (RelativeLayout) view.findViewById(R.id.viewTabView);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            ivIcon = (ImageView) view.findViewById(R.id.ivIcon);

            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            view.setLayoutParams(layoutParams);

            if (isCenter) {
                LayoutParams rLayoutParams = (LayoutParams) viewTabView.getLayoutParams();
                rLayoutParams.height = DensityUtil.dp2px(88);
                viewTabView.setLayoutParams(rLayoutParams);
                ViewGroup.LayoutParams params = ivIcon.getLayoutParams();
                params.height = DensityUtil.dp2px(60);
                params.width = DensityUtil.dp2px(60);
                ivIcon.setLayoutParams(params);
            }
            tvTitle.setText(title);

        }

        /**
         * 设置状态
         */
        public void setStatus(int status) {
            tvTitle.setTextColor(ContextCompat.getColor(super.getContext(), status == PRESS ? colorPress : colorDef));
            ivIcon.setImageResource(status == PRESS ? iconResPress : iconResDef);
        }

    }
}
