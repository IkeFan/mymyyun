package com.mmy.maimaiyun.model.login.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.login.activity.RegisterActivity;
import com.mmy.maimaiyun.model.login.module.RegisterModule;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/8/24 0024 11:07
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = {RegisterModule.class},dependencies = AppComponent.class)
public interface RegisterComponent {
    void inject(RegisterActivity activity);
}
