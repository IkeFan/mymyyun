package com.mmy.maimaiyun.model.personal.ui.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.model.personal.adapter.PhotoUploadAdapter;
import com.mmy.maimaiyun.popup.PictureSelectPopup;
import com.mmy.maimaiyun.utils.CommonUtil;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

/**
 * 图片上传
 */
public class PhotoUploadActivity extends BaseActivity implements BaseRecyclerViewAdapter.OnItemClickListener, View
        .OnClickListener, BaseRecyclerViewAdapter.OnItemLongClickListener {

    @Bind(R.id.list)
    RecyclerView         mRecyclerView;
    @Bind(R.id.title_root)
    View                 mRoot;
    @Bind(R.id.delete)
    FloatingActionButton mDeleted;
    @Bind(R.id.cancel)
    FloatingActionButton mCancel;
    @Bind(R.id.title_right_text)
    TextView             mRightText;
    @Bind(R.id.title_center_text)
    TextView             mTitle;

    /* 请求识别码 */
    private static final int    CODE_GALLERY_REQUEST = 0xa0;
    private static final int    CODE_CAMERA_REQUEST  = 0xa1;
    /* 头像文件 */
    private              String IMAGE_FILE_NAME      = "temp_head_image.jpg";
    private PictureSelectPopup                       mPictureSelectPopup;
    private PhotoUploadAdapter                       mAdapter;
    private LinkedList<PhotoUploadAdapter.PhotoBean> mData;

    private             int mMaxPic         = 10;//最大上传数量，intent使用
    private             int mMinPic         = 1;//最小上传数量，intent使用
    public static final int PIC_RESULT_CODE = 0xf000;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        PhotoAttrs attr = (PhotoAttrs) getIntent().getSerializableExtra("attr");
        mMaxPic = attr.maxPhoto;
        mMinPic = attr.minPhoto;
        mRightText.setText("完成");
        mTitle.setText(attr.title);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new PhotoUploadAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPictureSelectPopup = new PictureSelectPopup(this, this);
    }

    @Override
    public void initData() {
        mData = new LinkedList<>();
        mAdapter.setData(mData);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        mDeleted.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mRightText.setOnClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_photo;
    }

    @Override
    public void onItemClick(View view, int position) {
        //判断上传数量是否上限
        if (mData.size() >= mMaxPic) {
            Toast.makeText(this, "最多只能选择" + mMaxPic + "张图片", Toast.LENGTH_SHORT).show();
            return;
        }
        //只响应无图的item
        if (position == mData.size()) {
            mPictureSelectPopup.showAsDropDown(mRoot);
        }
        checkItem(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_select_photo: // 从本地相册选取图片作为头像
                // 1. 使用默认风格，并指定选择数量：
                // 第一个参数Activity/Fragment； 第二个request_code； 第三个允许选择照片的数量，不填可以无限选择。
                // Album.startAlbum(this, ACTIVITY_REQUEST_SELECT_PHOTO, 9);

                // 2. 使用默认风格，不指定选择数量：
                // Album.startAlbum(this, ACTIVITY_REQUEST_SELECT_PHOTO); // 第三个参数不填的话，可以选择无数个。

                // 3. 指定风格，并指定选择数量，如果不想限制数量传入Integer.MAX_VALUE;
                Album.startAlbum(this, CODE_GALLERY_REQUEST
                    , mMaxPic                                                         // 指定选择数量。
                    , ContextCompat.getColor(this, R.color.colorPrimary)        // 指定Toolbar的颜色。
                    , ContextCompat.getColor(this, R.color.colorPrimaryDark));  // 指定状态栏的颜色。
                IMAGE_FILE_NAME = "pic_" + System.currentTimeMillis() + ".jpg";
                //                choseHeadImageFromGallery();
                break;
            case R.id.bt_select_takephoto: // 启动手机相机拍摄照片作为头像
                IMAGE_FILE_NAME = "pic_" + System.currentTimeMillis() + ".jpg";
                choseHeadImageFromCameraCapture();
                break;
            case R.id.delete:
                mDeleted.setVisibility(View.GONE);
                mCancel.setVisibility(View.GONE);
                mAdapter.isShowDeleted(false);
                List<PhotoUploadAdapter.PhotoBean> list = mAdapter.delete();
                Log.d("PhotoUploadActivity", "list.size():" + list.size());
                //同步数据
                mData = (LinkedList<PhotoUploadAdapter.PhotoBean>) list;
                break;
            case R.id.cancel:
                mDeleted.setVisibility(View.GONE);
                mCancel.setVisibility(View.GONE);
                mAdapter.isShowDeleted(false);
                break;
            case R.id.title_right_text:
                resultData();
                break;
        }
        if (mPictureSelectPopup.isShowing())
            mPictureSelectPopup.dismiss();
    }

    private void resultData() {
        //判断下限
        if (mData.size() < mMinPic) {
            Toast.makeText(this, "请最少选择" + mMinPic + "张图片", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("data", mData);
        setResult(PIC_RESULT_CODE, intent);
        finish();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        Uri data = null;
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                // 拿到用户选择的图片路径List：
                List<String> pathList = Album.parseResult(intent);
                for (String path : pathList) {
                    mData.add(new PhotoUploadAdapter.PhotoBean(path));
                }
                mAdapter.setData(mData);
                //                data = intent.getData();
                break;
            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(Environment.getExternalStorageDirectory(),
                                             IMAGE_FILE_NAME);
                    data = Uri.fromFile(tempFile);
                } else {
                    Toast.makeText(getApplicationContext(), "没有SDCard!", Toast.LENGTH_LONG).show();
                }
                String path = CommonUtil.getPathByUri4kitkat(this, data);
                mData.add(new PhotoUploadAdapter.PhotoBean(path));
                mAdapter.setData(mData);
                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    //    private void refresTodayhList(Uri data) {
    //        //            Bitmap bitmap = CommonUtil.getBitmapFormUri(this, data);
    //        String path = CommonUtil.getPathByUri4kitkat(this, data);
    //        mData.add(new PhotoUploadAdapter.PhotoBean(path));
    //        mAdapter.setData(mData);
    //    }

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

    @Override
    public void onItemLongClick(View view, int position) {
        mAdapter.isShowDeleted(true);
        if (position == mData.size())
            return;
        mDeleted.setVisibility(View.VISIBLE);
        mCancel.setVisibility(View.VISIBLE);
        checkItem(position);
    }

    private void checkItem(int position) {
        if (position == mData.size())
            return;
        PhotoUploadAdapter.PhotoBean bean = mData.get(position);
        bean.isDelete = !bean.isDelete;
        mData.remove(position);
        mData.add(position, bean);
        mAdapter.setData(mData);
    }

    public static class PhotoAttrs implements Serializable {
        String title    = "图片上传";//标题
        int    maxPhoto = 10;//最大上传数量
        int    minPhoto = 1;//最小上传数量
        String hint;//提示信息
    }
}
