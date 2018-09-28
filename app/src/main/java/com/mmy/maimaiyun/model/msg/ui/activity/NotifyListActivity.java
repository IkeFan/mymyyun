package com.mmy.maimaiyun.model.msg.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.customview.SpacesItemDecoration;
import com.mmy.maimaiyun.model.msg.adapter.NotifyListAdapter;
import com.mmy.maimaiyun.model.msg.component.DaggerNotifyListComponent;
import com.mmy.maimaiyun.model.msg.module.NotifyListModule;
import com.mmy.maimaiyun.model.msg.presenter.NotifyListPresenter;
import com.mmy.maimaiyun.model.msg.view.NotifyListView;
import com.mmy.maimaiyun.utils.UIUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 通知
 */
public class NotifyListActivity extends BaseActivity<NotifyListPresenter> implements NotifyListView {

    public static final int ORDER       = 0;//订单通知
    public static final int SYSTEM      = 1;//系统通知
    public static       int currentType = -1;

    @Bind(R.id.title_center_text)
    TextView     mTitle;
    @Bind(R.id.list)
    RecyclerView mList;
    private NotifyListAdapter mAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerNotifyListComponent.builder()
                .appComponent(appComponent)
                .notifyListModule(new NotifyListModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        currentType = getIntent().getIntExtra("pageType", -1);
        mTitle.setText(currentType == ORDER ? "订单通知" : "系统通知");
        mList.setLayoutManager(new LinearLayoutManager(this));
        int px = UIUtil.dp2px(this, 7);
        mList.addItemDecoration(new SpacesItemDecoration(px, 0, px * 2, px * 2));
        mAdapter = new NotifyListAdapter(this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_notify_list;
    }

    @Override
    public void refreshList(List<String> data) {
        mAdapter.setData(data,currentType);
    }
}
