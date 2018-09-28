package com.mmy.maimaiyun.model.login.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.login.activity.BindPhoneActivity;
import com.mmy.maimaiyun.model.login.module.BindPhoneModule;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/11/2 0002 10:49
 * @描述
 */
@ActivityScoped
@Component(modules = BindPhoneModule.class,dependencies = AppComponent.class)
public interface BindPhoneComponent {
    void inject(BindPhoneActivity activity);
}
