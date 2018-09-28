package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseRefreshActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.db.RecordBean;
import com.mmy.maimaiyun.model.personal.adapter.BrowseHistoryAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerBrowseHistoryComponent;
import com.mmy.maimaiyun.model.personal.module.BrowseHistoryModule;
import com.mmy.maimaiyun.model.personal.presenter.BrowseHistoryPresenter;
import com.mmy.maimaiyun.model.personal.view.BrowseHistoryView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 浏览记录
 */
public class BrowseHistoryActivity extends BaseRefreshActivity<BrowseHistoryPresenter> implements BrowseHistoryView,
        BaseRecyclerViewAdapter.OnItemClickListener {

    @Bind(R.id.title_center_text)
    TextView           mTitle;
    @Bind(R.id.title_right_text)
    TextView           mRight;
    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartLayout;
    RecyclerView mList;
    @Bind(R.id.delete_container)
    View mDeleteContainer;
    private BrowseHistoryAdapter mAdapter;
    private SweetAlertDialog     mSweetAlertDialog;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerBrowseHistoryComponent.builder()
                .appComponent(appComponent)
                .browseHistoryModule(new BrowseHistoryModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("我的足迹");
        mRight.setText("清空");
        mList = mLoadingSmartLayout.getListView();
        mList.setLayoutManager(new GridLayoutManager(this, 2));
        int dp3 = UIUtil.dp2px(this, 2);
        mList.addItemDecoration(new SpacesItemDecoration(dp3 * 2, 0, dp3, dp3));
        mAdapter = new BrowseHistoryAdapter(this);
        mList.setAdapter(mAdapter);
        mSweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog
                .setTitleText("提醒")
                .setContentText("是否确认清空浏览记录?")
                .setCancelText("取消")
                .setConfirmText("确认");
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                mPresenter.clear();
            }
        });
        mAdapter.addOnItemCliclListener(this);
    }

    @OnClick({R.id.title_right_text})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_right_text:
                mSweetAlertDialog.show();
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_browse_history;
    }

    @Override
    public void refreshList(ArrayList<RecordBean> data) {
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        mAdapter.setData(data);
    }

    @Override
    public void onItemClick(View view, int position) {
        mPresenter.openGoodInfo(position);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.loadData();
    }
}
