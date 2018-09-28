package com.mmy.maimaiyun.wxapi;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/16 0016 10:46
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = WXPayModule.class,dependencies = AppComponent.class)
public interface WXPayComponent {
    void inject(WXPayEntryActivity activity);
}
