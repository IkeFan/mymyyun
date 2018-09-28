package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseRefreshActivity;
import com.mmy.maimaiyun.customview.LoadingSmartLayout;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.UserBean;
import com.mmy.maimaiyun.model.personal.adapter.MyUsersAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerMyUsersComponent;
import com.mmy.maimaiyun.model.personal.module.MyUsersModule;
import com.mmy.maimaiyun.model.personal.presenter.MyUsersPresenter;
import com.mmy.maimaiyun.model.personal.view.MyUsersView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 我的会员
 */
public class MyUsersActivity extends BaseRefreshActivity<MyUsersPresenter> implements MyUsersView {

    @Bind(R.id.title_center_text)
    TextView           mTitle;
    @Bind(R.id.loading_smart_layout)
    LoadingSmartLayout mLoadingSmartLayout;
    RecyclerView mList;
    private MyUsersAdapter mAdapter;
    public static final int level_1 = 1;
    public static final int level_2 = 2;
    public static final int level_3 = 3;
    private int mLevel;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerMyUsersComponent.builder()
                .appComponent(appComponent)
                .myUsersModule(new MyUsersModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("我的会员");
        mList = mLoadingSmartLayout.getListView();
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 2);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new MyUsersAdapter(this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mLevel = getIntent().getIntExtra("level", level_3);
        mPresenter.loadData(mLevel);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_my_users;
    }

    @Override
    public void refreshList(List<UserBean> data) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setData(data);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.loadData(mLevel);
    }
}
