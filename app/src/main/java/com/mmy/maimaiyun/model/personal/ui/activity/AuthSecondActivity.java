package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.popup.PictureSelectPopup;
import com.mmy.maimaiyun.utils.CommonUtil;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 实名认证2
 */
public class AuthSecondActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_center_text)
    TextView  mTitle;
    @Bind(R.id.front_img)
    ImageView mFrontImg;
    @Bind(R.id.opposite_img)
    ImageView mOppositeImg;

    /* 请求识别码 */
    private static final int    CODE_GALLERY_REQUEST = 0xa0;
    private static final int    CODE_CAMERA_REQUEST  = 0xa1;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME      = "temp_head_image.jpg";
    private PictureSelectPopup mPictureSelectPopup;
    private ImageView          mSelectIV;
    private String[] mImags = new String[2];


    @Override
    protected void initDagger(AppComponent appComponent) {
    }

    @Override
    public void initView() {
        mTitle.setText("实名认证");
        mPictureSelectPopup = new PictureSelectPopup(this, this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.opposite, R.id.front, R.id.submit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.submit:
                submit();
                break;
            case R.id.front:
                mSelectIV = mFrontImg;
                mPictureSelectPopup.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            case R.id.opposite:
                mSelectIV = mOppositeImg;
                mPictureSelectPopup.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
        }
    }

    private void submit() {
        if (TextUtils.isEmpty(mImags[0])||TextUtils.isEmpty(mImags[1])){
            Toast.makeText(this, "请上传身份证的正反照", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = getIntent();
        String name = data.getStringExtra("name");
        String certificatesCode = data.getStringExtra("certificatesCode");
        String address = data.getStringExtra("address");
        String addressDetail = data.getStringExtra("addressDetail");
        data.putExtra("imgs",mImags);
        data.setClass(this,AuthingActivity.class);
        startActivity(data);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_auth_second;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_select_photo: // 从本地相册选取图片作为头像
                choseHeadImageFromGallery();
                break;
            case R.id.bt_select_takephoto: // 启动手机相机拍摄照片作为头像
                choseHeadImageFromCameraCapture();
                break;
        }
        if (mPictureSelectPopup.isShowing())
            mPictureSelectPopup.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        //        Uri data = intent.getData();
        Uri data = null;
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                data = intent.getData();
                break;
            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    data = Uri.fromFile(tempFile);
                } else {
                    Toast.makeText(getApplicationContext(), "没有SDCard!", Toast.LENGTH_LONG).show();
                }
                break;
        }
        refreshList(data);
        super.onActivityResult(requestCode, resultCode, intent);
    }

    private void refreshList(Uri data) {
        String path = CommonUtil.getPathByUri4kitkat(this, data);
        Picasso.with(this).load(new File(path)).into(mSelectIV);
        mImags[mSelectIV == mFrontImg ? 0 : 1] = path;
    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }
        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

}
