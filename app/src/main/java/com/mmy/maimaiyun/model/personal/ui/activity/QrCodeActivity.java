package com.mmy.maimaiyun.model.personal.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.utils.UIUtil;

import butterknife.Bind;

/**
 * 店铺二维码
 */
public class QrCodeActivity extends BaseActivity {

    @Bind(R.id.title_center_text)
    TextView  mTitle;
    @Bind(R.id.shop_icon)
    ImageView mShopIcon;
    @Bind(R.id.shop_name)
    TextView  mShopName;
    @Bind(R.id.qr_code)
    ImageView mQrCode;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("店铺二维码");
        mQrCode.setImageBitmap(UIUtil.createQRCode("www.baidu.com", UIUtil.dp2px(this, 200)));
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_qr_code;
    }
}
