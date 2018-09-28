package com.mmy.maimaiyun.model.main.ui.activity;

import android.util.Patterns;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;

import butterknife.Bind;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 用户协议
 */
public class WebActivity extends BaseActivity {

    @Bind(R.id.content)
    WebView  mContent;
    @Bind(R.id.title_center_text)
    TextView mTitle;
    private SweetAlertDialog mDialog;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("扫描结果");
        mDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
        mDialog.setTitleText("错误")
                .setConfirmText("返回")
                .setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismiss();
                    finish();
                });
    }

    @Override
    public void initData() {
        String qr_code = getIntent().getStringExtra("qr_code");
        if (!Patterns.WEB_URL.matcher(qr_code).matches() && !URLUtil.isValidUrl(qr_code)) {
            mDialog.setContentText("扫描内容：" + qr_code);
            mDialog.show();
            return;
        }
        mContent.getSettings().setJavaScriptEnabled(true);
        mContent.loadUrl(qr_code);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_agreement;
    }
}
