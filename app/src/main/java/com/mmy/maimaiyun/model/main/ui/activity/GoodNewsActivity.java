package com.mmy.maimaiyun.model.main.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.GoodNewsClassBean;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;
import com.mmy.maimaiyun.model.main.adapter.GoodNewsPagerAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerGoodNewsComponent;
import com.mmy.maimaiyun.model.main.module.GoodNewsModule;
import com.mmy.maimaiyun.model.main.presenter.GoodNewsPresenter;
import com.mmy.maimaiyun.model.main.view.GoodNewsView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 商城头条
 */
public class GoodNewsActivity extends BaseActivity<GoodNewsPresenter> implements GoodNewsView, TabLayout
        .OnTabSelectedListener, SwipeRefreshLayout.OnRefreshListener, GoodNewsPagerAdapter.OnNewsClickListener {

    @Bind(R.id.tabs)
    TabLayout          mTabs;
    @Bind(R.id.pager)
    ViewPager          mViewPager;
    @Bind(R.id.title_center_text)
    TextView           mTitle;
    @Bind(R.id.refresh_view)
    SwipeRefreshLayout mRefreshLayout;
    private GoodNewsPagerAdapter mAdapter;
    private int                  mPosition;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerGoodNewsComponent.builder()
                .appComponent(appComponent)
                .goodNewsModule(new GoodNewsModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("商城头条");
        mTabs.setupWithViewPager(mViewPager);
        mAdapter = new GoodNewsPagerAdapter(this);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadClass();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mTabs.addOnTabSelectedListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnNewsClickListener(this);
    }

    @OnClick({R.id.search})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra("type", SearchActivity.NEWS);
                startActivity(intent);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_good_news;
    }


    @Override
    public void refreshClass(List<GoodNewsClassBean> data) {
        mAdapter.setClassData(data);
    }

    @Override
    public void refreshList(List<GoodNewsItemBean> data, int position) {
        if (mRefreshLayout.isRefreshing())
            mRefreshLayout.setRefreshing(false);
        mAdapter.setData(data, position);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mPosition = tab.getPosition();
        mPresenter.loadNewsData(mPosition);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onRefresh() {
        mPresenter.loadNewsData(mPosition);
    }

    @Override
    public void onNewsClick(int index, int position) {
        mPresenter.openNewsInfoPage(index, position);
    }
}
