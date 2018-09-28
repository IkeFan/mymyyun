package com.mmy.maimaiyun.model.main.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.adapter.BaseQuickAdapter;
import com.mmy.maimaiyun.adapter.BaseViewHolder;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.NewGoodBean;
import com.mmy.maimaiyun.model.main.adapter.NewGoodAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerNewGoodComponent;
import com.mmy.maimaiyun.model.main.module.NewGoodModule;
import com.mmy.maimaiyun.model.main.presenter.NewGoodPresenter;
import com.mmy.maimaiyun.model.main.view.NewGoodView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 新品推出
 */
public class NewGoodsActivity extends BaseActivity<NewGoodPresenter> implements NewGoodView, BaseQuickAdapter
        .OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.title_center_text)
    TextView     mTitleCenterText;
    @Bind(R.id.list)
    RecyclerView mList;
    private NewGoodAdapter mAdapter;
    private String         mLastId;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerNewGoodComponent.builder()
                .appComponent(appComponent)
                .newGoodModule(new NewGoodModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("新品推出");
        mList.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new NewGoodAdapter(R.layout.item_ac_only_new, new ArrayList<>());
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadData(false);
    }

    @Override
    public void initEvent() {
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnLoadMoreListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_new_goods;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, BaseViewHolder baseViewHolder, int position) {
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadData(true);
    }

    @Override
    public void refreshView(List<NewGoodBean.GoodsBean> goods, boolean isLoadMore) {
        if (isLoadMore) {
            if (goods == null || goods.size() == 0 || mLastId.equals(goods.get(goods.size() - 1).id))
                mAdapter.loadMoreEnd();
            mAdapter.addData(goods);
            mLastId = goods.get(goods.size() - 1).id;
        } else
            mAdapter.setNewData(goods);
        mAdapter.loadMoreComplete();
    }
}
