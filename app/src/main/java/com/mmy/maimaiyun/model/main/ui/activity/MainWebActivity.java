package com.mmy.maimaiyun.model.main.ui.activity;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;

import butterknife.Bind;

public class MainWebActivity extends BaseActivity {

    String mTitle ;
    @Bind(R.id.title_center_text)
    TextView mTitleCenterText;
    @Bind(R.id.web)
    WebView  mWeb;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle = getResources().getString(R.string.app_name);
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        mTitle = TextUtils.isEmpty(title) ? mTitle : title;
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(this, "地址错误", Toast.LENGTH_SHORT).show();
            return;
        }
        mTitleCenterText.setText(mTitle);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main_web;
    }

}
