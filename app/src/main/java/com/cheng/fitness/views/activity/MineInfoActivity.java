package com.cheng.fitness.views.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cheng.baselib.mvpbase.BasePresenter;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.utils.ActivityManager;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.ConfigConstant;
import com.cheng.fitness.common.constant.Constant;
import com.cheng.fitness.model.UserBean;
import com.cheng.fitness.utils.GreenDaoUtil;
import com.cheng.fitness.views.widget.bottomsheet.BottomSheet;
import com.cheng.fitness.views.widget.bottomsheet.listener.OnMenuItemClickListener;
import com.cheng.fitness.views.widget.dialog.InputDialog;
import com.cheng.fitness.views.widget.dialog.MLAlertDialog;
import com.hss01248.photoouter.PhotoCallback;
import com.hss01248.photoouter.PhotoUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.addapp.pickers.picker.DatePicker;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * author: PengCheng
 * time: 2018/5/28 0028
 * desc: 我的信息
 */

public class MineInfoActivity extends BaseActivity {

    private final int TAKE_PHOTO_CODE = 100;
    private final int CHOOSE_PHOTO_CODE = 101;

    @Bind(R.id.avatarLayout)
    LinearLayout avatarLayout;
    @Bind(R.id.tvNickname)
    TextView tvNickname;
    @Bind(R.id.nicknameLayout)
    LinearLayout nicknameLayout;
    @Bind(R.id.tvGender)
    TextView tvGender;
    @Bind(R.id.genderLayout)
    LinearLayout genderLayout;
    @Bind(R.id.birthdayLayout)
    LinearLayout birthdayLayout;
    @Bind(R.id.tvBirthday)
    TextView tvBirthday;
    @Bind(R.id.tvLogout)
    TextView tvLogout;
    @Bind(R.id.avatarImage)
    CircleImageView avatarImage;

    private UserBean mUserBean;

    private PhotoCallback mPhotoCallback;

    private boolean isChoosePhoto = false;

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_info;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        mPhotoCallback = new PhotoCallback() {
            @Override
            public void onFail(String s, Throwable throwable, int requestCode) {
            }

            @Override
            public void onSuccessSingle(String s, String compressedPath, int requestCode) {
                mUserBean.setAvatar(compressedPath);
                ConfigConstant.setKeyUserAvatar(compressedPath);
                GreenDaoUtil.updateUser(mUserBean);
                Glide.with(MineInfoActivity.this).load(compressedPath).into(avatarImage);
            }

            @Override
            public void onSuccessMulti(List<String> list, List<String> compressedPaths, int requestCode) {
            }

            @Override
            public void onCancel(int requestCode) {
            }
        };
    }

    @Override
    public void loadData() {
        super.loadData();
        mUserBean = GreenDaoUtil.getUser(ConfigConstant.getKeyUserNickname());
    }

    @Override
    public void reLoadData() {
        super.reLoadData();
        tvNickname.setText(ConfigConstant.getKeyUserNickname());
        if (!TextUtils.isEmpty(ConfigConstant.getKeyUserGender()))
            tvGender.setText(ConfigConstant.getKeyUserGender());
        setData();
    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
        toolBar.setTitleText("我的信息");
    }

    @OnClick({R.id.avatarLayout, R.id.nicknameLayout, R.id.genderLayout, R.id.birthdayLayout, R.id.tvLogout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatarLayout:
                handleAvatar();
                break;
            case R.id.nicknameLayout:
                handleNickname();
                break;
            case R.id.genderLayout:
                handleGender();
                break;
            case R.id.birthdayLayout:
                handleBirthdayLayout();
                break;
            case R.id.tvLogout:
                handleLogout();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoUtil.onActivityResult(MineInfoActivity.this, requestCode, resultCode, data);
    }

    private void handleAvatar() {
        final BottomSheet bottomSheet = BottomSheet.Builder
                .newBuilder(this)
                .setItem(new BottomSheet.MenuItem("拍照", 0, "tag1"))
                .setItem(new BottomSheet.MenuItem("从相册选择", 0, "tag2"))
                .build();
        bottomSheet.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onItemClick(BottomSheet.MenuItem item) {
                bottomSheet.dismiss();
                if (TextUtils.equals("拍照", item.name)) {
                    isChoosePhoto = false;
                } else {
                    isChoosePhoto = true;
                }
                getPermission();
            }
        });
        bottomSheet.show();
    }

    @AfterPermissionGranted(Constant.MY_PERMISSIONS_REQUEST_RECORD_CAMERA)
    private void getPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(MineInfoActivity.this, perms)) {
            if (isChoosePhoto) {
                ChoosePhoto();
            } else {
                TakePhoto();
            }
        } else {
            EasyPermissions.requestPermissions(MineInfoActivity.this, "需要你的相机权限",
                    Constant.MY_PERMISSIONS_REQUEST_RECORD_CAMERA, perms);
        }
    }

    //拍照
    private void TakePhoto() {
        PhotoUtil.cropAvatar(true)
                .start(MineInfoActivity.this, TAKE_PHOTO_CODE, mPhotoCallback);
    }

    //从相册选图片
    private void ChoosePhoto() {
        PhotoUtil.begin()
                .setNeedCropWhenOne(true)
                .setNeedCompress(true)
                .setMaxSelectCount(1)
                .setCropMuskOval()
                .start(MineInfoActivity.this, CHOOSE_PHOTO_CODE, mPhotoCallback);
    }

    private void handleBirthdayLayout() {
        final DatePicker picker = new DatePicker(this);
        picker.setCanLoop(true);
        picker.setWheelModeEnable(true);
        picker.setTopPadding(15);
        picker.setRangeStart(1900, 1, 1);
        picker.setRangeEnd(2019, 1, 1);
        picker.setSelectedItem(1990, 1, 1);
        picker.setWeightEnable(true);
        picker.setLineColor(Color.BLACK);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                if (mUserBean != null) {
                    mUserBean.setBirthday(year + "-" + month + "-" + day);
                    GreenDaoUtil.updateUser(mUserBean);
                    tvBirthday.setText(year + "-" + month + "-" + day);
                }
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    private void handleNickname() {
        InputDialog dialog = new InputDialog(context)
                .setTitle("编辑昵称")
                .setContent(ConfigConstant.getKeyUserNickname())
                .setMaxLength(15)
                .setSingleLine(true)
                .highlightRight()
                .setListener(new InputDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog, View view, int which, String input) {
                        if (which == InputDialog.LEFT) return;
                        if (TextUtils.isEmpty(input)) {
                            showToast("请输入昵称");
                        } else {
                            tvNickname.setText(input);
                            if (mUserBean != null) {
                                mUserBean.setNickname(input);
                                GreenDaoUtil.updateUser(mUserBean);
                                ConfigConstant.setKeyUserNickname(input);
                            }
                        }
                    }
                });
        dialog.show();
    }

    private void handleGender() {
        List<String> items = new ArrayList<>();
        items.add("男");
        items.add("女");
        MLAlertDialog mlAlertDialog = new MLAlertDialog(MineInfoActivity.this, null, items);
        mlAlertDialog.init(new MLAlertDialog.OnItemClickListner() {
            @Override
            public void onClick(int position) {
                String genderStr = null;
                switch (position) {
                    case 0:
                        genderStr = "男";
                        break;
                    case 1:
                        genderStr = "女";
                        break;
                }
                tvGender.setText(genderStr);
                if (mUserBean != null) {
                    mUserBean.setGender(genderStr);
                    GreenDaoUtil.updateUser(mUserBean);
                    ConfigConstant.setKeyUserGender(genderStr);
                }
            }
        });
    }

    private void handleLogout() {
        ConfigConstant.setKeyUserPassword("");
        ConfigConstant.setKeyUserGender("");
        ConfigConstant.setKeyUserAvatar("");
        ActivityManager.getAppInstance().finishAllActivity();
        startActivity(new Intent(MineInfoActivity.this, LoginActivity.class));
    }

    private void setData() {
        if (mUserBean != null && !TextUtils.isEmpty(mUserBean.getBirthday())) {
            tvBirthday.setText(mUserBean.getBirthday());
        } else if (mUserBean !=null && !TextUtils.isEmpty(mUserBean.getAvatar())) {
            Glide.with(MineInfoActivity.this).load(mUserBean.getAvatar()).into(avatarImage);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
