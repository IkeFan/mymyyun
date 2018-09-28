package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.RefundListBean;
import com.mmy.maimaiyun.model.personal.adapter.RefundListAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerRefundListComponent;
import com.mmy.maimaiyun.model.personal.module.RefundListModule;
import com.mmy.maimaiyun.model.personal.presenter.RefundListPresenter;
import com.mmy.maimaiyun.model.personal.view.RefundListView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 退款/售后
 */
public class RefundListActivity extends BaseActivity<RefundListPresenter> implements RefundListView, BaseQuickAdapter
        .OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.list)
    RecyclerView mList;
    private RefundListAdapter mAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerRefundListComponent.builder()
                .appComponent(appComponent)
                .refundListModule(new RefundListModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("退款/售后");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 7);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new RefundListAdapter(R.layout.item_refund_list, new ArrayList<>());
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadData(false);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnLoadMoreListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_refund_list;
    }

    @Override
    public void refreshList(List<RefundListBean> data, boolean isLoadMore) {
        if (isLoadMore)
            if (data.size() == 0)
                mAdapter.loadMoreEnd();
            else
                mAdapter.addData(data);
        else
            mAdapter.setNewData(data);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, BaseViewHolder baseViewHolder, int position) {
//        Intent intent = new Intent(this, RefundInfoActivity.class);
//        intent.putExtra("bean", (RefundListBean) adapter.getData().get(position));
//        startActivity(intent);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadData(true);
    }
}
