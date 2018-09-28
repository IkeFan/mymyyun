package com.mmy.maimaiyun.model.msg.ui.activity;

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
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.data.bean.MessageBean;
import com.mmy.maimaiyun.model.msg.adapter.MsgAdapter;
import com.mmy.maimaiyun.model.msg.component.DaggerMsgComponent;
import com.mmy.maimaiyun.model.msg.module.MsgModule;
import com.mmy.maimaiyun.model.msg.presenter.MsgPresenter;
import com.mmy.maimaiyun.model.msg.view.MsgView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 消息
 */
public class MsgActivity extends BaseActivity<MsgPresenter> implements SwipeRefreshLayout.OnRefreshListener, MsgView,
        BaseRecyclerViewAdapter.OnItemClickListener {

    @Bind(R.id.list)
    RecyclerView       mList;
    @Bind(R.id.refresh_view)
    SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.title_center_text)
    TextView           mTitle;
    private MsgAdapter mAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerMsgComponent.builder()
                .appComponent(appComponent)
                .msgModule(new MsgModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("消息");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 1);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, 0, 0));
        mAdapter = new MsgAdapter(this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public void initEvent() {
        mRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_msg;
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(() -> mRefreshLayout.setRefreshing(false), 1000);
    }

    @Override
    public void refreshList(ArrayList<MessageBean> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, NotifyListActivity.class);
        switch (position) {
            case 0://订单通知
                intent.putExtra("pageType", NotifyListActivity.ORDER);
                startActivity(intent);
                break;
            case 1://系统消息
                intent.putExtra("pageType", NotifyListActivity.SYSTEM);
                startActivity(intent);
                break;
            case 2://客服消息
                startActivity(new Intent(this,ConversationListActivity.class));
                break;
        }
    }
}
