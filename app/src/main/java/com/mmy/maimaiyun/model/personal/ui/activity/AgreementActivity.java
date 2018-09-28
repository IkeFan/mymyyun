package com.mmy.maimaiyun.model.personal.ui.activity;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;

import butterknife.Bind;

/**
 * 用户协议
 */
public class AgreementActivity extends BaseActivity {

    @Bind(R.id.content)
    WebView mContent;
    @Bind(R.id.title_center_text)
    TextView mTitle;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("用户服务协议");
        mContent.getSettings().setJavaScriptEnabled(true);
        mContent.loadUrl("file:///android_asset/agreement.html");
        mContent.addJavascriptInterface(this,"android");
    }

    @Override
    public void initData() {
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_agreement;
    }

    @JavascriptInterface
    public void onAlreadyRead(){
        finish();
        Toast.makeText(this, "已阅读", Toast.LENGTH_SHORT).show();
    }
}
