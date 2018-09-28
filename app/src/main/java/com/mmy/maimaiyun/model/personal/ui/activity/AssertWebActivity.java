package com.mmy.maimaiyun.model.personal.ui.activity;

import android.webkit.WebView;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;

import butterknife.Bind;

/**
 * 加载资源文件下的HTML文件
 */
public class AssertWebActivity extends BaseActivity {

    @Bind(R.id.content)
    WebView  mContent;
    @Bind(R.id.title_center_text)
    TextView mTitle;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        String title = getIntent().getStringExtra("title");
        String path = getIntent().getStringExtra("path");
        mTitle.setText(title);
        mContent.getSettings().setJavaScriptEnabled(true);
        mContent.loadUrl(path);
    }

    @Override
    public void initData() {
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_agreement;
    }

}
