package com.mmy.maimaiyun.model.splash.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.splash.activity.SplashActivity;
import com.mmy.maimaiyun.model.splash.module.SplashModule;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/8/16 0016 14:39
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = SplashModule.class,dependencies = AppComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}
