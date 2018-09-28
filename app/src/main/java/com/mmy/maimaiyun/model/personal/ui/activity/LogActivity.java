package com.mmy.maimaiyun.model.personal.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.LogBean;
import com.mmy.maimaiyun.model.personal.adapter.LogAdapter;
import com.mmy.maimaiyun.model.personal.component.DaggerLogComponent;
import com.mmy.maimaiyun.model.personal.module.LogModule;
import com.mmy.maimaiyun.model.personal.presenter.LogPresenter;
import com.mmy.maimaiyun.model.personal.view.LogView;

import butterknife.Bind;

/**
 * 物流信息
 */
public class LogActivity extends BaseActivity<LogPresenter> implements LogView {


    @Bind(R.id.title_center_text)
    TextView     mTitleCenterText;
    @Bind(R.id.order_id)
    TextView     mOrderId;
    @Bind(R.id.log_id)
    TextView     mLogId;
    @Bind(R.id.list)
    RecyclerView mList;
    private LogAdapter mAdapter;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerLogComponent.builder()
                .appComponent(appComponent)
                .logModule(new LogModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitleCenterText.setText("物流跟踪");
        mList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new LogAdapter(this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        String code = getIntent().getStringExtra("code");
        String name = getIntent().getStringExtra("name");
        String shipping = getIntent().getStringExtra("shipping");
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(code)) {
            Toast.makeText(this, "后台物流数据不完整", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        mOrderId.setText(shipping);
        mLogId.setText(code);
        mPresenter.loadData(name, code);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_log;
    }

    @Override
    public void refreshView(LogBean logBean) {
        mAdapter.setData(logBean);
    }
}
