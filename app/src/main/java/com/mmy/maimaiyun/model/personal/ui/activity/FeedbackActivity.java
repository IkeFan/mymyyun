package com.mmy.maimaiyun.model.personal.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.personal.component.DaggerFeedbackComponent;
import com.mmy.maimaiyun.model.personal.module.FeedbackModule;
import com.mmy.maimaiyun.model.personal.presenter.FeedbackPresenter;
import com.mmy.maimaiyun.model.personal.view.FeedbackView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 反馈
 */
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements FeedbackView{

    @Bind(R.id.title_center_text)
    TextView mTitle;
    @Bind(R.id.content)
    EditText mContent;

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerFeedbackComponent.builder()
                .appComponent(appComponent)
                .feedbackModule(new FeedbackModule(this))
                .build().inject(this);
    }

    @Override
    public void initView() {
        mTitle.setText("反馈");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.submit})
    public void click(View view){
        switch (view.getId()) {
            case R.id.submit:
                String trim = mContent.getText().toString().trim();
                if (TextUtils.isEmpty(trim)){
                    Toast.makeText(this, "请输入您的建议", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.submit(trim);
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_feedback;
    }
}
