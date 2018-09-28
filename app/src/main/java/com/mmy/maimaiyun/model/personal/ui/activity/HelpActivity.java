package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 帮助与反馈
 */
public class HelpActivity extends BaseActivity {

    @Bind(R.id.title_center_text)
    TextView mTitle;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("帮助与反馈");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.server, R.id.about, R.id.feedback})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.server:
                startActivity(new Intent(this, AgreementActivity.class));
                break;
            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_help;
    }
}
