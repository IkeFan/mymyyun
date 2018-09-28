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
import com.mmy.maimaiyun.data.bean.CollectionBean;
import com.mmy.maimaiyun.model.personal.adapter.CollectionAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerCollectionComponent;
import com.mmy.maimaiyun.model.personal.module.CollectionModule;
import com.mmy.maimaiyun.model.personal.presenter.CollectionPresenter;
import com.mmy.maimaiyun.model.personal.view.CollectionView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的收藏
 */
public class CollectionActivity extends BaseRefreshActivity<CollectionPresenter> implements CollectionView,
        BaseRecyclerViewAdapter.OnItemClickListener {

    @Bind(R.id.title_center_text)
    TextView           mTitle;
    @Bind(R.id.title_right_text)
    TextView           mRight;
    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartLayout;
    RecyclerView mList;
    @Bind(R.id.edit_container)
    View mEdit;

    private CollectionAdapter mAdapter;
    private boolean           mIsEdit;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerCollectionComponent.builder()
                .appComponent(appComponent)
                .collectionModule(new CollectionModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mList = mLoadingSmartLayout.getListView();
        mTitle.setText("我的收藏");
        mRight.setText("编辑");
        int px = UIUtil.dp2px(this, 3);
        mList.setLayoutManager(new GridLayoutManager(this, 2));
        mList.addItemDecoration(new SpacesItemDecoration(px));
        mAdapter = new CollectionAdapter(this);
        mList.setAdapter(mAdapter);
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

    @OnClick({R.id.title_right_text, R.id.delete})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.title_right_text:
                mIsEdit = !mIsEdit;
                if (mIsEdit) {
                    mRight.setText("完成");
                    mEdit.setVisibility(View.VISIBLE);
                    mRight.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    mRight.setText("编辑");
                    mEdit.setVisibility(View.GONE);
                    mRight.setTextColor(getResources().getColor(R.color.normal_text_color));
                }
                mAdapter.isEdit(mIsEdit);
                break;
            case R.id.delete://删除
                mPresenter.delCollect(mAdapter.getCheckedIds());
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_collection;
    }

    @Override
    public void refresh(List<CollectionBean> data) {
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        mAdapter.setData(data);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mIsEdit)
            return;
        mPresenter.openGoodInfoPage(position);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.loadData();
    }
}
