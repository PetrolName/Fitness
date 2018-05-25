package com.cheng.baselib.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.baselib.R;
import com.cheng.baselib.view.dialog.anim.Rotate3d;


/**
 * Description: 3D转动图片loading的dialog（全屏透明）
 */
public class LoadingDialog extends Dialog {
    private static final String TAG = "YzsLoadingDialog";
    private TextView tvMessage;
    private ImageView yzsLoading;
    private Context context;
    /**
     * 下方显示message
     */
    private String message;

    private Drawable drawable;

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
        this.context = context;
    }

    public LoadingDialog(Context context, String message) {
        super(context, R.style.LoadingDialog);
        this.context = context;
        this.message = message;
    }

    public LoadingDialog(Context context, String message, Drawable drawable) {
        super(context, R.style.LoadingDialog);
        this.context = context;
        this.message = message;
        this.drawable = drawable;
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public LoadingDialog(Context context, Drawable drawable) {
        super(context, R.style.LoadingDialog);
        this.drawable = drawable;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.layout_loading_dialog);
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(false);
        yzsLoading = (ImageView) findViewById(R.id.iv_res_loading_dialog);
        tvMessage = (TextView) findViewById(R.id.tv_res_loading_dialog_message);
        if (message != null) {
            tvMessage.setText(message);
        }
        if (null == drawable) {
            yzsLoading.setImageDrawable(context.getResources().getDrawable(R.mipmap.icon));
        } else {
            yzsLoading.setImageDrawable(drawable);
        }
        Rotate3d rotate3d = new Rotate3d();
        rotate3d.setDuration(2000);
        rotate3d.setRepeatCount(Integer.MAX_VALUE);
        rotate3d.setRepeatMode(2);
        yzsLoading.startAnimation(rotate3d);
    }

    public void show(String message) {
        this.message = message;
        if (tvMessage != null && message != null) {
            tvMessage.setText(message);
        }
        super.show();
    }

    public void show(int msgResId) {
        show(getContext().getString(msgResId));
    }


    public LoadingDialog setYzsMessage(String message) {
        this.message = message;
        if (tvMessage != null && message != null) {
            tvMessage.setText(message);
        }
        return this;
    }

    public LoadingDialog setYzsMessage(int resId) {
        this.message = getContext().getString(resId);
        if (tvMessage != null && message != null) {
            tvMessage.setText(message);
        }
        return this;
    }

    @Override
    public void hide() {
        if (null != yzsLoading) {
            yzsLoading.clearAnimation();
        }
        super.hide();
    }

    @Override
    public void dismiss() {
        if (null != yzsLoading) {
            yzsLoading.clearAnimation();
        }
        super.dismiss();
    }
}
