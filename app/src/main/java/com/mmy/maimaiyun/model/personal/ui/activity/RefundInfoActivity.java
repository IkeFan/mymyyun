package com.mmy.maimaiyun.model.personal.ui.activity;

import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.RefundListBean;

import butterknife.Bind;

/**
 * 退款详情
 */
public class RefundInfoActivity extends BaseActivity {

    @Bind(R.id.title_center_text)
    TextView mTitle;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("退款详情");
    }

    @Override
    public void initData() {
        RefundListBean bean = (RefundListBean) getIntent().getSerializableExtra("bean");

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_refund_info;
    }
}
