package com.mmy.maimaiyun.wxapi;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/11/4 0004 10:39
 * @描述
 */
@ActivityScoped
@Component(modules = WXCustomModule.class,dependencies = AppComponent.class)
public interface WXCustomComponent {
    void inject(WXEntryActivity activity);
}
