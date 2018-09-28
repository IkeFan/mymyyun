package com.mmy.maimaiyun.model.splash.module;

import com.mmy.maimaiyun.model.splash.view.SplashView;

import dagger.Module;
import dagger.Provides;

/**
 * @创建者 lucas
 * @创建时间 2017/8/16 0016 14:37
 * @描述 TODO
 */

@Module
public class SplashModule {
    private SplashView mSplashView;

    public SplashModule(SplashView splashView) {
        mSplashView = splashView;
    }

    @Provides
    SplashView provideSplashView(){
        return mSplashView;
    }

}
