package com.mmy.maimaiyun.model.main.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.CommentBean;
import com.mmy.maimaiyun.model.main.adapter.AllCommentAdapter;
import com.mmy.maimaiyun.model.main.component.DaggerAllCommentComponent;
import com.mmy.maimaiyun.model.main.module.AllCommentModule;
import com.mmy.maimaiyun.model.main.presenter.AllCommentPresenter;
import com.mmy.maimaiyun.model.main.view.AllCommentView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 商品评价
 */
public class AllCommentActivity extends BaseActivity<AllCommentPresenter> implements AllCommentView, TabLayout
        .OnTabSelectedListener {

    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.tabs)
    TabLayout    mTabs;
    private AllCommentAdapter mAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerAllCommentComponent.builder()
                .allCommentModule(new AllCommentModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("商品评价");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 2);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new AllCommentAdapter(this);
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        String goods_id = getIntent().getStringExtra("goods_id");
        mPresenter.loadData(goods_id,true);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mTabs.addOnTabSelectedListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_all_comment;
    }

    @Override
    public void refreshList(List<CommentBean> data) {
        mAdapter.setData(data);
        ArrayList<CommentBean> lista = new ArrayList<>();//好评
        ArrayList<CommentBean> listb = new ArrayList<>();//中评
        ArrayList<CommentBean> listc = new ArrayList<>();//差评
        for (CommentBean bean : data) {
            switch (Integer.parseInt(bean.star)) {
                case 0:
                case 1:
                    listc.add(bean);
                    break;
                case 2:
                    listb.add(bean);
                    break;
                case 3:
                case 4:
                case 5:
                    lista.add(bean);
                    break;
            }
        }
        mTabs.addTab(mTabs.newTab().setText("全部评论(" + (data == null ? 0 : data.size()) + ")"));
        mTabs.addTab(mTabs.newTab().setText("好评(" + lista.size() + ")"));
        mTabs.addTab(mTabs.newTab().setText("中评(" + listb.size() + ")"));
        mTabs.addTab(mTabs.newTab().setText("差评(" + listc.size() + ")"));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mAdapter.select(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
