package com.mmy.maimaiyun.model.login.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.login.activity.LoginActivity;
import com.mmy.maimaiyun.model.login.module.LoginModule;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/8/12 0012 16:08
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity view);
}
