package com.mmy.maimaiyun.model.splash.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.BuildConfig;
import com.mmy.maimaiyun.R;
import com.mmy.maimaiyun.base.activity.BaseActivity;
import com.mmy.maimaiyun.model.city.DBHelper;
import com.mmy.maimaiyun.model.login.activity.LoginActivity;
import com.mmy.maimaiyun.model.main.ui.activity.MainActivity;
import com.mmy.maimaiyun.model.splash.component.DaggerSplashComponent;
import com.mmy.maimaiyun.model.splash.module.SplashModule;
import com.mmy.maimaiyun.model.splash.preserter.SplashPresenter;
import com.mmy.maimaiyun.model.splash.view.SplashView;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * 启动页面
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initDagger(AppComponent appComponent) {
        DaggerSplashComponent.builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build().inject(this);
    }

    @Override
    public boolean isFullscreen() {
        return true;
    }

    @Override
    public void initView() {
        ImageView view = (ImageView) findViewById(R.id.root);
        Picasso.with(this).load(BuildConfig.SPLASH_BG).into(view);
    }

    @Override
    public void initData() {
        copyAssets();
        mPresenter.showGuidePage();
    }

    //复制资源
    private void copyAssets() {
        try {
            new DBHelper(this).createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void openLoginPage() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void openMainPage() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
