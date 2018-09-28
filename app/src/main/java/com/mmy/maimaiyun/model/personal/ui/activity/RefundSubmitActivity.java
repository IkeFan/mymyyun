package com.mmy.maimaiyun.model.personal.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.data.bean.BankCarBean;
import com.mmy.maimaiyun.data.bean.OrderBean;
import com.mmy.maimaiyun.model.personal.adapter.PhotoUploadAdapter;
import com.mmy.maimaiyun.model.personal.adapter.RefundInfoAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerRefundSubmitComponent;
import com.mmy.maimaiyun.model.personal.module.RefundSubmitModule;
import com.mmy.maimaiyun.model.personal.presenter.RefundSubmitPresenter;
import com.mmy.maimaiyun.model.personal.view.RefundSubmitView;
import com.mmy.maimaiyun.popup.GoodStatusPopup;
import com.mmy.maimaiyun.popup.RefundReasonPopup;
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
 * 退款、退货提交界面
 */
public class RefundSubmitActivity extends BaseActivity<RefundSubmitPresenter> implements RefundReasonPopup
        .OnRefundItemClickListener,
        GoodStatusPopup.OnItemClickListener, BaseRecyclerViewAdapter.OnItemClickListener, RefundSubmitView {
    @Bind(R.id.order_icon)
    ImageView    mOrderIcon;
    @Bind(R.id.order_name)
    TextView     mOrderName;
    @Bind(R.id.order_clazz)
    TextView     mOrderClazz;
    @Bind(R.id.order_money)
    TextView     mOrderMoney;
    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.money_text)
    TextView     mMoney;
    @Bind(R.id.money_more)
    TextView     mMoneyMore;
    @Bind(R.id.good_status)
    View         mGoodStatus;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.explain)
    EditText     mExplain;
    @Bind(R.id.reason)
    TextView     mReasonTV;
    @Bind(R.id.status)
    TextView     mStatusTV;
    @Bind(R.id.bank_info)
    TextView     mBankInfo;
    /* 请求识别码 */
    private static final int    CODE_GALLERY_REQUEST = 0xa0;
    private static final int    CODE_CAMERA_REQUEST  = 0xa1;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME      = "temp_head_image.jpg";
    private int               mType;//页面类型
    private RefundReasonPopup mRefundReasonPopup;
    private GoodStatusPopup   mGoodStatusPopup;
    private RefundInfoAdapter mAdapter;
    private int maxPic = 6;

    LinkedList<PhotoUploadAdapter.PhotoBean> mData = new LinkedList<>();
    private String mReason;//退款原因
    private int mStatus = -1;//货物状态
    private BankCarBean mBean;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerRefundSubmitComponent.builder()
                .appComponent(appComponent)
                .refundSubmitModule(new RefundSubmitModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mType = getIntent().getIntExtra("type", -1);
        if (mType == RefundActivity.RETURN_GOOD || mType == RefundActivity.RETURN_MONEY) {
            mTitle.setText("申请退款");
        } else {
            mTitle.setText("申请退货");
            //            mGoodStatus.setVisibility(View.GONE);
            mMoney.setVisibility(View.GONE);
            mMoneyMore.setVisibility(View.GONE);
        }
        mRefundReasonPopup = new RefundReasonPopup(this);
        mGoodStatusPopup = new GoodStatusPopup(this);
        mList.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new RefundInfoAdapter(this);
        mList.setAdapter(mAdapter);
        mList.setNestedScrollingEnabled(false);
    }

    @Override
    public void initData() {
        //商品信息
        OrderBean.GoodsInfoBean good = (OrderBean.GoodsInfoBean) getIntent().getSerializableExtra("good");
        if (good == null)
            return;
        if (!TextUtils.isEmpty(good.logo))
            Picasso.with(this).load(good.logo).error(R.mipmap.ic_def).into(mOrderIcon);
        mOrderName.setText(good.goods_name);
        mOrderClazz.setText(good.goods_attr_value);
        int color = getResources().getColor(R.color.colorPrimary);
        mOrderMoney.setText(Html.fromHtml("合计￥<font color='" + color + "'>" + good.price + "</font>(含运费￥" + good
                .shipping_price + ") " + "数量 " + good.goods_number + " 件"));
        float price = good.price;
        int count = Integer.parseInt(good.goods_number);
        mMoney.setText("￥" + price * count);
        mMoneyMore.setText("最多退款 ￥" + price * count + "，含发货邮费 ￥" + good.shipping_price);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mRefundReasonPopup.setOnItemClickListener(this);
        mGoodStatusPopup.setOnGoodStatusItemClickListener(this);
        mAdapter.addOnItemCliclListener(this);
    }

    @OnClick({R.id.refund_reason, R.id.good_status, R.id.submit, R.id.select_bank_card})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.select_bank_card:
                Intent intent = new Intent(this, BankCarActivity.class);
                intent.putExtra("action", BankCarActivity.ACTION_SELECT_BANK_CARD);
                startActivityForResult(intent, BankCarActivity.ACTION_SELECT_BANK_CARD);
                break;
            case R.id.submit:
                submit();
                break;
            case R.id.refund_reason:
                mRefundReasonPopup.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            case R.id.good_status:
                mGoodStatusPopup.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
        }
    }

    //提交数据
    private void submit() {
        if (mStatus == -1) {
            Toast.makeText(this, "请选择货物状态", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mReason)) {
            Toast.makeText(this, "请选择退款原因", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(this, "提交失败，请退出重试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mBean == null) {
            Toast.makeText(this, "请先选择银行卡", Toast.LENGTH_SHORT).show();
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("member_id", getUserBean().member_id + "");
        builder.addFormDataPart("goods_status", mStatus + "");
        builder.addFormDataPart("after_why", mReason);
        builder.addFormDataPart("shop_id", intent.getStringExtra("shop_id"));
        builder.addFormDataPart("order_goods_id", intent.getStringExtra("order_goods_id"));
        builder.addFormDataPart("info", mExplain.getText().toString().trim());
        builder.addFormDataPart("after_type", mType + "");
        builder.addFormDataPart("bank_id", mBean.id);

        int index = 0;
        for (PhotoUploadAdapter.PhotoBean photoBean : mData) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), new File
                    (photoBean.path));
            builder.addFormDataPart("credentials_pic" + "[" + index++ + "]", CommonUtil.getFileName(photoBean.path),
                    requestBody);
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        mPresenter.submitData(parts);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_refund_submit;
    }

    @Override
    public void onRefundItemClick(String info, int position) {
        mReason = info;
        mReasonTV.setText(info);
    }

    @Override
    public void onGoodStatusItemClick(String info, int position) {
        mStatus = position;
        mStatusTV.setText(info);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if (data != null) {
            switch (requestCode) {
                case BankCarActivity.ACTION_SELECT_BANK_CARD:
                    mBean = (BankCarBean) data.getSerializableExtra("bean");
                    String bank_card = mBean.bank_card;
                    mBankInfo.setText(mBean.name + " (尾号" + bank_card.substring(bank_card.length() - 4, bank_card
                            .length()) + ")");
                    break;
            }
        }
        if (resultCode == PhotoUploadActivity.PIC_RESULT_CODE) {
            Serializable extra = data.getSerializableExtra("data");
            ArrayList<PhotoUploadAdapter.PhotoBean> pics = null;
            pics = (ArrayList<PhotoUploadAdapter.PhotoBean>) extra;
            mData.addAll(pics);
        }
        if (requestCode == 0x10) {
            //更新图片备注
            int position = data.getIntExtra("position", 0);
            String name = data.getStringExtra("name");
            mData.get(position).msg = name;
        }
        mAdapter.setData(mData);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (position == mData.size()) {
            if (mData.size() >= 6) {
                Toast.makeText(this, "最多上传6张图片", Toast.LENGTH_SHORT).show();
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
}
