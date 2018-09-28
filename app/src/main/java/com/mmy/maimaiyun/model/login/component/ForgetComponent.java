package com.mmy.maimaiyun.model.login.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.login.activity.ForgetActivity;
import com.mmy.maimaiyun.model.login.module.ForgetModule;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/8/24 0024 11:43
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = {ForgetModule.class},dependencies = AppComponent.class)
public interface ForgetComponent {
    void inject(ForgetActivity activity);
}
