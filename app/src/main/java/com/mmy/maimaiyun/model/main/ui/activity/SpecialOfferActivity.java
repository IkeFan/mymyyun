package com.mmy.maimaiyun.model.main.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.SubCateBean;
import com.mmy.maimaiyun.data.bean.SubGoodsInfoBean;
import com.mmy.maimaiyun.model.main.adapter.SpecialOfferAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerSpecialOfferComponent;
import com.mmy.maimaiyun.model.main.module.SpecialOfferModule;
import com.mmy.maimaiyun.model.main.presenter.SpecialOfferPresenter;
import com.mmy.maimaiyun.model.main.view.SpecialOfferView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 天天特价
 */
public class SpecialOfferActivity extends BaseActivity<SpecialOfferPresenter> implements SpecialOfferView, TabLayout
        .OnTabSelectedListener, SwipeRefreshLayout.OnRefreshListener, BaseRecyclerViewAdapter.OnItemClickListener {

    @Bind(R.id.toolbar)
    View         mTitleRoot;
    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.tabs)
    TabLayout    mTabs;
    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartLayout;
    RecyclerView mList;
    @Bind(R.id.refresh_view)
    SwipeRefreshLayout mRefreshLayout;
    private SpecialOfferAdapter mAdapter;
    private int mSearchType;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerSpecialOfferComponent.builder()
                .appComponent(appComponent)
                .specialOfferModule(new SpecialOfferModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mList = mLoadingSmartLayout.getListView();
        mTitle.setTextColor(0xffffffff);
        mTitleRoot.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mList.setLayoutManager(new GridLayoutManager(this, 2));
        int px = UIUtil.dp2px(this, 5);
        mList.addItemDecoration(new SpacesItemDecoration(px));
        mAdapter = new SpecialOfferAdapter(this);
        mList.setAdapter(mAdapter);
        //设置活动类型
        String activityType = getIntent().getStringExtra("activity_type");
        mTitle.setText(activityType);
        mSearchType = getIntent().getIntExtra("search_type", SearchActivity.SPECIAL);
        mPresenter.setActivityType(activityType);
    }

    @Override
    public void initData() {
        mPresenter.loadMenuData();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mTabs.addOnTabSelectedListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_special_offer;
    }

    @Override
    public void refreshList(List<SubGoodsInfoBean> data) {
        if (mRefreshLayout.isRefreshing())
            mRefreshLayout.setRefreshing(false);
        mAdapter.setData(data);
    }

    @Override
    public void refreshMenu(List<SubCateBean> data) {
        for (SubCateBean datum : data) {
            mTabs.addTab(mTabs.newTab().setText(datum.cat_name));
        }
    }

//    @OnClick({R.id.search})
//    public void click(View view){
//        switch (view.getId()) {
//            case R.id.search:
//                Intent intent = new Intent(this, SearchActivity.class);
//                intent.putExtra("type",mSearchType);
//                startActivity(intent);
//                break;
//        }
//    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mPresenter.selectTab(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onRefresh() {
        mPresenter.selectTab(mTabs.getSelectedTabPosition());
    }

    @Override
    public void onItemClick(View view, int position) {
        mPresenter.openInfoPage(position);
    }
}
