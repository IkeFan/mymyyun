package com.mmy.maimaiyun.model.classity.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseRefreshActivity;
import com.mmy.maimaiyun.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.ClassityBean;
import com.mmy.maimaiyun.model.classity.adapter.ClassityInfoAdapter;
import com.mmy.maimaiyun.model.classity.component.DaggerClassInfoComponent;
import com.mmy.maimaiyun.model.classity.module.ClassInfoModule;
import com.mmy.maimaiyun.model.classity.presenter.ClassInfoPresenter;
import com.mmy.maimaiyun.model.classity.view.ClassIInfoView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 分类详细
 */
public class ClassInfoActivity extends BaseRefreshActivity<ClassInfoPresenter> implements ClassIInfoView,
        BaseRecyclerViewAdapter.OnItemClickListener {

    @Bind(R.id.title_center_text)
    TextView     mTitleCenterText;
    @Bind(R.id.list)
    RecyclerView mList;
    private ClassityInfoAdapter mAdapter;
    private String              mCat_id;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerClassInfoComponent.builder()
                .appComponent(appComponent)
                .classInfoModule(new ClassInfoModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mAdapter = new ClassityInfoAdapter(this);
        mList.setLayoutManager(new GridLayoutManager(this, 2));
        int px = UIUtil.dp2px(this, 5);
        mList.addItemDecoration(new SpacesItemDecoration(0, px, 0, px));
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mCat_id = getIntent().getStringExtra("cat_id");
        String name = getIntent().getStringExtra("name");
        String isShow = getIntent().getStringExtra("isShow");
        mAdapter.showPrice2("1".equals(isShow));
        mTitleCenterText.setText(name);
        mPresenter.loadData(mCat_id);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_class_info;
    }

    @Override
    public void refreshView(List<ClassityBean> data) {
        hideRefreshView();
        mAdapter.setData(data);
    }

    @Override
    public void loadFailde() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(View view, int position) {
        mPresenter.openInfoPage(position);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.loadData(mCat_id);
    }
}
