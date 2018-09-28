package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.base.activity.BaseRefreshActivity;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.TradeRecordBean;
import com.mmy.maimaiyun.model.personal.adapter.TradeRecordAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerTradeRecordComponent;
import com.mmy.maimaiyun.model.personal.module.TradeRecordModule;
import com.mmy.maimaiyun.model.personal.presenter.TradeRecordPresenter;
import com.mmy.maimaiyun.model.personal.view.TradeRecordView;
import com.mmy.maimaiyun.popup.TradeRecordPopup;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 交易记录
 */
public class TradeRecordActivity extends BaseRefreshActivity<TradeRecordPresenter> implements TradeRecordView,
        TradeRecordPopup.OnTradeRecordPopupItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.title_center_text)
    TextView           mTitle;
    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartLayout;
    RecyclerView mList;
    @Bind(R.id.clazz_text)
    TextView  mClazzText;
    @Bind(R.id.clazz_icon)
    ImageView mClazzIcon;
    private TradeRecordAdapter mAdapter;
    private TradeRecordPopup   mRecordPopup;
    private int mTypeIndex;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerTradeRecordComponent.builder()
                .appComponent(appComponent)
                .tradeRecordModule(new TradeRecordModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mList = mLoadingSmartLayout.getListView();
        mTitle.setText("交易记录");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 2);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new TradeRecordAdapter(R.layout.item_trade_record, new ArrayList<>());
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadData(false);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.setOnLoadMoreListener(this);
    }

    @OnClick({R.id.clazz_text})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.clazz_text:
                if (mRecordPopup == null)
                    return;
                if (mRecordPopup.isShowing()) {
                    mRecordPopup.dismiss();
                    return;
                }
                mRecordPopup.showAsDropDown((View) view.getParent(), 5, 10);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_trade_record;
    }

    @Override
    public void refreshList(List<TradeRecordBean> data, boolean isLoadMore) {
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        if (data == null || data.size() == 0) {
            mAdapter.loadMoreEnd();
            return;
        }
        if (isLoadMore) {
            mAdapter.addData(data);
            mAdapter.loadMoreComplete();
        } else
            mAdapter.setNewData(data);
        mAdapter.refreshAllData();
    }

    @Override
    public void refreshMenuList(LinkedHashMap<Integer, String> map) {
        mRecordPopup = new TradeRecordPopup.Builder(this)
                .setData(map).builde();
        mRecordPopup.setOnTradeRecordPopupItemClickListener(this);
    }

    @Override
    public void onTradeItemClick(int index, String msg) {
        mTypeIndex = index;
        mRecordPopup.dismiss();
        mClazzText.setText(msg);
        mAdapter.order(index);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.loadData(false);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadData(true);
    }
}
