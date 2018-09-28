package com.mmy.maimaiyun.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mmy.maimaiyun.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @创建者 lucas
 * @创建时间 2017/11/6 0006 15:43
 * @描述 针对 listview 做 empty error loading 三状态切换
 */

public class LoadingSmartLayout extends LinearLayout {
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.loading_view)
    LinearLayout mLoadingView;
    @Bind(R.id.empty_view)
    LinearLayout mEmptyView;
    @Bind(R.id.retry)
    TextView     mRetry;
    @Bind(R.id.error_view)
    LinearLayout mErrorView;
    @Bind(R.id.network_view)
    LinearLayout mNetworkView;

    public enum Status {
        SUCCESS(0), EMPTY(1), ERROR(2), LOADING(3),NOT_NETWORK(4);
        int value;

        Status(int value) {
            this.value = value;
        }
    }

    public LoadingSmartLayout(Context context) {
        super(context);
        initView(context);
    }


    public LoadingSmartLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_smart_loading, this, false);
        ButterKnife.bind(this, view);
        addView(view);
        //默认加载中
        switchStatus(Status.LOADING);
    }

    //切换状态
    public void switchStatus(Status status) {
        mList.setVisibility(GONE);
        mEmptyView.setVisibility(GONE);
        mLoadingView.setVisibility(GONE);
        mRetry.setVisibility(GONE);
        mErrorView.setVisibility(GONE);
        mNetworkView.setVisibility(GONE);
        if (status == Status.SUCCESS) {
            mList.setVisibility(VISIBLE);
        }
        if (status == Status.EMPTY) {
            mEmptyView.setVisibility(VISIBLE);
        }
        if (status == Status.ERROR) {
            mErrorView.setVisibility(VISIBLE);
        }
        if (status == Status.LOADING) {
            mLoadingView.setVisibility(VISIBLE);
        }
        if (status == Status.NOT_NETWORK) {
            mNetworkView.setVisibility(VISIBLE);
        }
    }

    public RecyclerView getListView() {
        return mList;
    }


    public TextView getRetry() {
        return mRetry;
    }
}
