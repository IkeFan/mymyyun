package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.able.OnItemInnerClickListener;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.model.personal.adapter.TransactionManagerAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerTransactionManagerComponent;
import com.mmy.maimaiyun.model.personal.module.TransactionManagerModule;
import com.mmy.maimaiyun.model.personal.presenter.TransactionManagerPresenter;
import com.mmy.maimaiyun.model.personal.view.TransactionManagerView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;

public class TransactionManagerActivity extends BaseActivity<TransactionManagerPresenter> implements
        TransactionManagerView, BaseRecyclerViewAdapter.OnItemClickListener, OnItemInnerClickListener {

    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.tabs)
    TabLayout    mTabLayout;

    String[] mTabStrs = {"全部", "待付款", "待收货", "交易成功", "售后/退款"};
    private TransactionManagerAdapter mAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerTransactionManagerComponent.builder()
                .appComponent(appComponent)
                .transactionManagerModule(new TransactionManagerModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("交易管理");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 7);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new TransactionManagerAdapter(this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadData();
        for (String str : mTabStrs) {
            mTabLayout.addTab(mTabLayout.newTab().setText(str));
        }
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemInnerClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_transation_manager;
    }

    @Override
    public void refresh(List<String> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(this, OrderInfoActivity.class));
    }

    @Override
    public void onItemInnerClick(View view, int position, int type) {
        switch (type) {
            case TransactionManagerAdapter.CUSTOMER_SERVICE:
                startActivity(new Intent(this, BusinessCustomerServiceActivity.class));
                break;
        }
    }
}
