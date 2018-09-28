package com.mmy.maimaiyun.base.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.mvp.BasePresenter;

/**
 * @创建者 lucas
 * @创建时间 2017/10/24 0024 9:18
 * @描述 集成此类可以获得刷新功能
 */

public abstract class BaseRefreshActivity<P extends BasePresenter> extends BaseActivity<P> implements
        SwipeRefreshLayout.OnRefreshListener {

    protected SwipeRefreshLayout mRefreshLayout;

    @Override
    protected boolean initRefreshView() {
        mRefreshLayout = new SwipeRefreshLayout(this);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        View view = LayoutInflater.from(this).inflate(getContentViewId(), mRefreshLayout, false);
        mRefreshLayout.addView(view);
        setContentView(mRefreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        return true;
    }

    @Override
    public void onRefresh() {
    }

    public void showRefreshView() {
        if (!mRefreshLayout.isRefreshing())
            mRefreshLayout.setRefreshing(true);
    }

    public void hideRefreshView() {
        if (mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
            Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
        }
    }
}
