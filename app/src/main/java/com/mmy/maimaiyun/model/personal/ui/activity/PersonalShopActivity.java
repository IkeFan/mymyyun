package com.mmy.maimaiyun.model.personal.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;

import butterknife.Bind;

/**
 * 我的店铺
 */
public class PersonalShopActivity extends BaseActivity {

    @Bind(R.id.title_center_text)
    TextView  mTitleCenterText;
    @Bind(R.id.icon)
    ImageView mIcon;
    @Bind(R.id.shop_head_img)
    ImageView mShopHeadImg;
    @Bind(R.id.sign)
    ImageView mSign;
    @Bind(R.id.advertisement)
    ImageView mAdvertisement;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitleCenterText.setText("商铺设置");
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_personal_shop;
    }

}
