package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.adapter.SubDistributionPagerAdapter;

import butterknife.Bind;

/**
 * 分销管理
 */
public class SubDistributionActivity extends BaseActivity {

    @Bind(R.id.pager)
    ViewPager mPager;
    @Bind(R.id.title_center_text)
    TextView  mTitle;
    @Bind(R.id.tabs)
    TabLayout mTabs;


    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("分销管理");
        String[] titles = {"下级分销商(0)", "下级的下级分销商(0)"};
        mTabs.setupWithViewPager(mPager);
        mPager.setAdapter(new SubDistributionPagerAdapter(this, titles));
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_sub_distribution;
    }
}
