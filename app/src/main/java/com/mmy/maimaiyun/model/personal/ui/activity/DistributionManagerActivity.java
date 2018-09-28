package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.adapter.DistributionAdapter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 分销管理
 */
public class DistributionManagerActivity extends BaseActivity {

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
        mPager.setAdapter(new DistributionAdapter(this, titles));
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.show_next})
    public void click(View view){
        switch (view.getId()) {
            case R.id.show_next:
                startActivity(new Intent(this,SubDistributionActivity.class));
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_distribution_manager;
    }
}
