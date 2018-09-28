package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.AddressItemBean;
import com.mmy.maimaiyun.model.personal.adapter.AddressManagerAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerAddressManagerComponent;
import com.mmy.maimaiyun.model.personal.module.AddressManagerModule;
import com.mmy.maimaiyun.model.personal.presenter.AddressManagerPresenter;
import com.mmy.maimaiyun.model.personal.view.AddressManagerView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 地址管理
 */
public class AddressManagerActivity extends BaseActivity<AddressManagerPresenter> implements AddressManagerView,
        AddressManagerAdapter.OnAddressItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseRecyclerViewAdapter.OnItemClickListener {

    @Bind(R.id.title_center_text)
    TextView           mTitle;
    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartView;
    @Bind(R.id.refresh_view)
    SwipeRefreshLayout mRefreshLayout;


    private AddressManagerAdapter mAdapter;
    private boolean               mIs_select;
    private List<AddressItemBean> mData;
    private RecyclerView          mList;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerAddressManagerComponent.builder()
                .appComponent(appComponent)
                .addressManagerModule(new AddressManagerModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mIs_select = getIntent().getBooleanExtra("is_select", false);
        int px = UIUtil.dp2px(this, 7);
        mTitle.setText("地址管理");
        mList = mLoadingSmartView.getListView();
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new AddressManagerAdapter(this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.loadData();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.setOnAddressClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        if (mIs_select)
            mAdapter.setOnItemClickListener(this);
    }

    @OnClick({R.id.title_back, R.id.add_new_address})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.add_new_address://添加新地址
                startActivity(new Intent(this, AddNewAddressActivity.class));
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_address_manager;
    }

    @Override
    public void refreshView(List<AddressItemBean> data) {
        //排序
        Collections.sort(data);
        mData = data;
        mRefreshLayout.setRefreshing(false);
        mAdapter.setData(data);
    }

    @Override
    public void onClick(View view, int type, int position) {
        switch (type) {
            case AddressManagerAdapter.MODIFY:
                mPresenter.openModifyPage(position);
                break;
            case AddressManagerAdapter.DELETE:
                mRefreshLayout.setRefreshing(true);
                mPresenter.delAddress(position);
                break;
            case AddressManagerAdapter.DEFAULT:
                //修改默认
                mPresenter.mdifDefaultAddress(position);
                mRefreshLayout.setRefreshing(true);
                break;
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.loadData();
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onItemClick(View view, int position) {
        AddressItemBean bean = mData.get(position);
        Intent data = new Intent();
        data.putExtra("bean",bean);
        setResult(0, data);
        finishSelf();
    }
}
