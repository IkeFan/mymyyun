package com.mmy.maimaiyun.model.personal.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.CacheUtils;
import com.mmy.maimaiyun.App;
import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.login.activity.LoginActivity;
import com.mmy.maimaiyun.utils.CommonUtil;
import com.mmy.maimaiyun.utils.GlobalUtil;

import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Cache;

/**
 * 系统设置
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.title_center_text)
    TextView mTitle;
    @Bind(R.id.cache_size)
    TextView mCacheSize;
    @Bind(R.id.version)
    TextView mVersion;
    private long mCacheSize1;
    private Cache mCache;


    @Override
    protected void initDagger(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        mTitle.setText("系统设置");
        mCache = App.getAppComponent().getCache();
        mVersion.setText("V"+ CommonUtil.getVersionName(this));
    }

    @Override
    public void initData() {
        //获取缓存大小
        showCacheSize();
    }

    private void showCacheSize() {
        try {
            mCacheSize1 = mCache.size()+ CacheUtils.getInstance(getCacheDir()).getCacheSize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("SettingActivity", "cacheSize:" + mCacheSize1);
        mCacheSize.setText(mCacheSize1 /1024f/1024f+"M");
    }

    @OnClick({R.id.login_out, R.id.cache_size_container})
    public void click(View view){
        switch (view.getId()) {
            case R.id.login_out:
                setUserBean(null);
                GlobalUtil.getInstance().clearUserInfo();
                closeAllActivity();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.cache_size_container:
                if (mCacheSize1!=0){
                    try {
                        mCache.delete();
                        CacheUtils.getInstance(getCacheDir()).clear();
                        Toast.makeText(this, "缓存清除成功", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    showCacheSize();
                }
                break;
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_setting;
    }
}
