package com.mmy.maimaiyun.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.popup.PictureSelectPopup;
import com.mmy.maimaiyun.utils.CommonUtil;
import com.mmy.maimaiyun.utils.PermissionUtil;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2017/10/18 0018 17:20
 * @描述 图片选择器
 */

public class PicSelectedHelper {

    /* 头像文件 */
    private static       String IMAGE_FILE_NAME      = "temp_head_image.jpg";
    /* 请求识别码 */
    private static final int    CODE_GALLERY_REQUEST = 0xa0;
    private static final int    CODE_CAMERA_REQUEST  = 0xa1;
    private static final int    CODE_RESULT_REQUEST  = 0xa2;
    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static       int    output_X             = 200;
    private static       int    output_Y             = output_X;
    private PictureSelectPopup mPopUp;

    private Activity            mActivity;
    private OnPicSelectListener mListener;
    private String              mPicPath;
    private int mMaxPic = 1;

    public PicSelectedHelper(Activity activity) {
        mActivity = activity;
        mPicPath = activity.getCacheDir().getAbsolutePath();
        initView();
        checkPermissions(activity);
    }

    private void checkPermissions(Activity activity) {
        //获取读取外部存储权限
        PermissionUtil.requestPermission(activity, Manifest.permission.CAMERA);
        PermissionUtil.requestPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        PermissionUtil.requestPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    private void initView() {
        mPopUp = new PictureSelectPopup(mActivity, itemsOnClick);
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            IMAGE_FILE_NAME = "pic_" + System.currentTimeMillis() + ".jpg";
            switch (v.getId()) {
                case R.id.bt_select_photo:
                    choseHeadImageFromGallery();
                    mPopUp.dismiss();
                    break;
                case R.id.bt_select_takephoto:
                    choseHeadImageFromCameraCapture();
                    mPopUp.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    public void showPopup() {
        View view = mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        mPopUp.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    /**
     * 在activity的onActivityResult中调用
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 用户没有进行有效的设置操作，返回
//        if (resultCode == RESULT_CANCELED) {
//            return;
//        }
        //        Uri data = intent.getData();

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                // 拿到用户选择的图片路径List：
                List<String> pathList = Album.parseResult(intent);
                if (pathList != null && pathList.size() > 0)
                    cropRawPhoto(Uri.fromFile(new File(pathList.get(0))));
                break;
            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(mActivity, "没有SDCard!", Toast.LENGTH_LONG).show();
                }
                break;
            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }
                break;
        }
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            final Bitmap photo = extras.getParcelable("data");
            //将图片存在本地
            String path = CommonUtil.saveBitmap(mActivity, photo, IMAGE_FILE_NAME);
            if (mListener != null) {
                mListener.onSelected(path, photo);
            }
        }
    }


    /**
     * 裁剪原始的图片
     */
    private void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);
        Uri newUri = Uri.parse("file:///" + mPicPath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, newUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        mActivity.startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Album.startAlbum(mActivity, CODE_GALLERY_REQUEST
                , mMaxPic                                                         // 指定选择数量。
                , ContextCompat.getColor(mActivity, R.color.colorPrimary)        // 指定Toolbar的颜色。
                , ContextCompat.getColor(mActivity, R.color.colorPrimaryDark));  // 指定状态栏的颜色。
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }
        mActivity.startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public void setOnPicSelectedListener(OnPicSelectListener listener) {
        mListener = listener;
    }

    public interface OnPicSelectListener {
        void onSelected(String path, Bitmap photo);
    }
}
