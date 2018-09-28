package com.mmy.maimaiyun.model.main.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseRefreshActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.ActivitySubCateBean;
import com.mmy.maimaiyun.data.bean.ActvityGoodInfoBean;
import com.mmy.maimaiyun.model.main.adapter.VolumeAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerVolumeComponent;
import com.mmy.maimaiyun.model.main.module.VolumeModule;
import com.mmy.maimaiyun.model.main.presenter.VolumePresenter;
import com.mmy.maimaiyun.model.main.view.VolumeView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 量贩优选
 */
public class VolumeActivity extends BaseRefreshActivity<VolumePresenter> implements VolumeView,
        BaseRecyclerViewAdapter.OnItemClickListener, VolumeAdapter.OnInnerClickListener {

    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartLayout;
    RecyclerView mList;
    private VolumeAdapter mAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerVolumeComponent.builder()
                .appComponent(appComponent)
                .volumeModule(new VolumeModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mList = mLoadingSmartLayout.getListView();
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 7);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new VolumeAdapter(this);
        mList.setAdapter(mAdapter);
        mAdapter.setOnInnerClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.addOnItemCliclListener(this);
    }

    @OnClick({R.id.search, R.id.jiantou})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra("type", SearchActivity.VOLUME);
                startActivity(intent);
                break;
            case R.id.jiantou:
                finishSelf();
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_volume;
    }

    @Override
    public void refreshList(List<ActivitySubCateBean<List<ActvityGoodInfoBean>>> data) {
        if (mRefreshLayout.isRefreshing())
            mRefreshLayout.setRefreshing(false);
        mAdapter.setData(data);
    }

    @Override
    public void onItemClick(View view, int position) {
        //        mPresenter.openInfoPage(position);
    }

    @Override
    public void onInnerClick(String id) {
        mPresenter.openInfoPage(id);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.loadData();
    }
}
