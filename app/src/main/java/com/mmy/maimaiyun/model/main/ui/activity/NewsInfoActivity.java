package com.mmy.maimaiyun.model.main.ui.activity;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.data.bean.GoodNewsItemBean;

import butterknife.Bind;

/**
 * 新闻详情
 */
public class NewsInfoActivity extends BaseActivity {

    @Bind(R.id.content)
    WebView  mContent;
    @Bind(R.id.title_center_text)
    TextView mTitle;
    private GoodNewsItemBean mBean;

    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        mBean = (GoodNewsItemBean) intent.getSerializableExtra("bean");
        if (mBean == null)
            return;
        mTitle.setText(mBean.title.length() > 6 ? mBean.title.substring(0, 5) : mBean.title);
    }

    @Override
    public void initData() {
        mContent.getSettings().setJavaScriptEnabled(true);
        String js = "<body>"+mBean.content+"<script type='text/javascript'>window.onload = function(){var $img = document.getElementsByTagName('img');for(var p in  $img){$img[p].style.width = '100%';$img[p].style.height ='auto'}}</script></body>";
        mContent.loadDataWithBaseURL(null, js, "text/html", "utf-8", null);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                //                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_news_info;
    }
}
