package com.cheng.fitness.views.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cheng.baselib.mvpbase.baseImpl.BaseActivity;
import com.cheng.baselib.view.ToolBarView;
import com.cheng.fitness.R;
import com.cheng.fitness.common.constant.Constant;
import com.cheng.fitness.contact.PublishContact;
import com.cheng.fitness.presenter.PublishPresenter;
import com.cheng.fitness.views.widget.bottomsheet.BottomSheet;
import com.cheng.fitness.views.widget.bottomsheet.listener.OnMenuItemClickListener;
import com.hss01248.photoouter.PhotoCallback;
import com.hss01248.photoouter.PhotoUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * author: PengCheng
 * time: 2018/5/30 0030
 * desc: 发布动态
 */

public class PublishActivity extends BaseActivity<PublishContact.presenter> implements PublishContact.view {
    private final int TAKE_PHOTO_CODE = 100;
    private final int CHOOSE_PHOTO_CODE = 101;
    @Bind(R.id.etPublishContent)
    EditText etPublishContent;
    @Bind(R.id.ivAddImage)
    ImageView ivAddImage;
    @Bind(R.id.ivImage)
    ImageView ivImage;
    @Bind(R.id.ivDelete)
    ImageView ivDelete;

    private PhotoCallback mPhotoCallback;

    private boolean isChoosePhoto = false;

    private String imageUrl;

    @Override
    public PublishContact.presenter initPresenter() {
        return new PublishPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void handleToolBar(ToolBarView toolBar) {
        super.handleToolBar(toolBar);
        toolBar.setVisibility(View.VISIBLE);
        toolBar.setTitleText("发布动态");
        toolBar.setRightText("发布");
        toolBar.setOnRightClickListener(new ToolBarView.OnBarRightClickListener() {
            @Override
            public void onRightClick(View v) {
                if (check()) mPresenter.publish(etPublishContent.getText().toString().trim(), imageUrl);
            }
        });
    }

    private boolean check() {
        if (TextUtils.isEmpty(etPublishContent.getText().toString().trim())) {
            showToast("动态内容不能为空");
            return false;
        }
        return true;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mPhotoCallback = new PhotoCallback() {
            @Override
            public void onFail(String s, Throwable throwable, int requestCode) {
            }

            @Override
            public void onSuccessSingle(String s, String compressedPath, int requestCode) {
                imageUrl = compressedPath;
                Glide.with(PublishActivity.this).load(compressedPath).into(ivImage);
                ivImage.setVisibility(View.VISIBLE);
                ivDelete.setVisibility(View.VISIBLE);
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
    public void onPublishSuccess() {
        showToast("发布成功");
        finish();
    }

    @Override
    public void onPublishFail(String msg) {
        showToast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoUtil.onActivityResult(PublishActivity.this, requestCode, resultCode, data);
    }

    @OnClick({R.id.ivAddImage, R.id.ivDelete, R.id.ivImage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivAddImage:
                handleAddPicture();
                break;
            case R.id.ivDelete:
                ivImage.setVisibility(View.GONE);
                ivDelete.setVisibility(View.GONE);
                break;
            case R.id.ivImage:

                break;
            default:
                break;
        }
    }

    private void handleAddPicture() {
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
        if (EasyPermissions.hasPermissions(PublishActivity.this, perms)) {
            if (isChoosePhoto) {
                ChoosePhoto();
            } else {
                TakePhoto();
            }
        } else {
            EasyPermissions.requestPermissions(PublishActivity.this, "需要你的相机权限",
                    Constant.MY_PERMISSIONS_REQUEST_RECORD_CAMERA, perms);
        }
    }

    //拍照
    private void TakePhoto() {
        PhotoUtil.cropAvatar(true)
                .setNeedCompress(false)
                .start(PublishActivity.this, TAKE_PHOTO_CODE, mPhotoCallback);
    }

    //从相册选图片
    private void ChoosePhoto() {
        PhotoUtil.begin()
                .setNeedCropWhenOne(true)
                .setNeedCompress(false)
                .setMaxSelectCount(1)
                .start(PublishActivity.this, CHOOSE_PHOTO_CODE, mPhotoCallback);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
