package com.mmy.maimaiyun.model.personal.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.OrderBean;
import com.mmy.maimaiyun.model.personal.adapter.PhotoUploadAdapter;
import com.mmy.maimaiyun.model.personal.adapter.RefundInfoAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerCommentComponent;
import com.mmy.maimaiyun.model.personal.module.CommentModule;
import com.mmy.maimaiyun.model.personal.presenter.CommentPresenter;
import com.mmy.maimaiyun.model.personal.view.CommentView;
import com.mmy.maimaiyun.utils.CommonUtil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 商品评价
 */
public class CommentActivity extends BaseActivity<CommentPresenter> implements CommentView,BaseRecyclerViewAdapter.OnItemClickListener {

    @Bind(R.id.title_center_text)
    TextView mTitleCenterText;

    @Bind(R.id.shop_name)
    TextView       mShopName;
    @Bind(R.id.good_container)
    LinearLayout   mGoodContainer;
    @Bind(R.id.star)
    RatingBar      mStar;
    @Bind(R.id.text)
    EditText       mText;
    @Bind(R.id.list)
    RecyclerView   mList;

    private RefundInfoAdapter mAdapter;
    LinkedList<PhotoUploadAdapter.PhotoBean> mData = new LinkedList<>();
    private int maxPic = 3;
    private OrderBean.GoodsInfoBean mGood;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerCommentComponent.builder()
                .appComponent(appComponent)
                .commentModule(new CommentModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("商品评论");
        mList.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new RefundInfoAdapter(this);
        mList.setAdapter(mAdapter);
        mList.setNestedScrollingEnabled(false);
    }

    @Override
    public void initData() {
        mGood = (OrderBean.GoodsInfoBean) getIntent().getSerializableExtra("goods");
        refreshView(mGood);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.addOnItemCliclListener(this);
    }

    private void refreshView(OrderBean.GoodsInfoBean shop) {
        mShopName.setText(getIntent().getStringExtra("shop_name"));
//        for (OrderBean.GoodsInfoBean infoBean : shop.goodsInfo) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_conf_order, mGoodContainer, false);
            mGoodContainer.addView(view);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            TextView goodName = (TextView) view.findViewById(R.id.good_name);
            TextView clazz = (TextView) view.findViewById(R.id.clazz);
            TextView money = (TextView) view.findViewById(R.id.money_text);
            TextView goodCount = (TextView) view.findViewById(R.id.good_count);
            if (!TextUtils.isEmpty(mGood.logo))
                Picasso.with(this).load(mGood.logo).into(icon);
            goodName.setText(mGood.goods_name);
            clazz.setText(mGood.goods_attr_value);
            money.setText("￥ " + mGood.price);
            goodCount.setText("数量 " + mGood.goods_number + " 件");
//        }
    }

    @OnClick({R.id.submit})
    public void click(View view){
        switch (view.getId()) {
            case R.id.submit:
                submit();
                break;
        }
    }

    private void submit() {
        String text = mText.getText().toString().trim();
        if (TextUtils.isEmpty(text)){
            Toast.makeText(this, "请输入评论内容", Toast.LENGTH_SHORT).show();
            return;
        }
        float rating = mStar.getRating();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("type", "0");
        int goods_id = mGood.goods_id;
        builder.addFormDataPart("object_id", goods_id +"");
        String member_id = getUserBean().member_id;
        builder.addFormDataPart("member_id", member_id+"");
        builder.addFormDataPart("content", text);
        builder.addFormDataPart("star", rating+"");
        String order_sn = getIntent().getStringExtra("order_sn");
        builder.addFormDataPart("order_sn", order_sn);

        int index = 0;
        for (PhotoUploadAdapter.PhotoBean photoBean : mData) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File
                    (photoBean.path));
            builder.addFormDataPart("pic" + "[" + index++ + "]", CommonUtil.getFileName(photoBean.path),
                    requestBody);
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        mPresenter.submit(parts);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if (resultCode == PhotoUploadActivity.PIC_RESULT_CODE) {
            Serializable extra = data.getSerializableExtra("data");
            ArrayList<PhotoUploadAdapter.PhotoBean> pics = null;
            pics = (ArrayList<PhotoUploadAdapter.PhotoBean>) extra;
            mData.addAll(pics);
            mAdapter.setData(mData);
        }

    }

    //打开找点上传界面
    private void openPicActivity(int picDoor, int min_pic, int max_pic, String title) {
        Intent intent = new Intent(this, PhotoUploadActivity.class);
        PhotoUploadActivity.PhotoAttrs attrs = new PhotoUploadActivity.PhotoAttrs();
        attrs.title = title;
        attrs.maxPhoto = max_pic;
        attrs.minPhoto = min_pic;
        intent.putExtra("attr", attrs);
        startActivityForResult(intent, picDoor);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_comment;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (position == mData.size()) {
            if (mData.size() >= 3) {
                Toast.makeText(this, "最多上传3张图片", Toast.LENGTH_SHORT).show();
            } else {//选择图片
                openPicActivity(0, 0, maxPic - mData.size(), "凭证照片");
            }
        } else {
            //预览
            Intent intent = new Intent(this, PrePhotoActivity.class);
            PhotoUploadAdapter.PhotoBean bean = mData.get(position);
            intent.putExtra("name", bean.msg);
            intent.putExtra("img", bean.path);
            intent.putExtra("position", position);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivityForResult(intent, 0x10, ActivityOptions.makeSceneTransitionAnimation(this,
                        view.findViewById(R.id.icon), "prePhoto").toBundle());
            } else {
                startActivityForResult(intent, 0x10);
            }
        }
    }
}
