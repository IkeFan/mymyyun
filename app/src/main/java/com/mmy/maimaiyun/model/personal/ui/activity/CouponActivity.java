package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.CouponListBean;
import com.mmy.maimaiyun.model.personal.adapter.CouponPagerAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerCouponComponent;
import com.mmy.maimaiyun.model.personal.module.CouponModule;
import com.mmy.maimaiyun.model.personal.presenter.CouponPresenter;
import com.mmy.maimaiyun.model.personal.view.CouponView;

import butterknife.Bind;

/**
 * 我的优惠券
 */
public class CouponActivity extends BaseActivity<CouponPresenter> implements CouponView {

    @Bind(R.id.title_center_text)
    TextView  mTitle;
    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.pager)
    ViewPager mViewPager;
    private CouponPagerAdapter mPagerAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerCouponComponent.builder()
                .appComponent(appComponent)
                .couponModule(new CouponModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("我的优惠券");
        mTabs.setupWithViewPager(mViewPager);
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_coupon;
    }

    @Override
    public void refreshList(CouponListBean data) {
        mPagerAdapter = new CouponPagerAdapter(this, new String[]{"未使用", "已使用", "已过期"}, data);
        mViewPager.setAdapter(mPagerAdapter);
    }
}
