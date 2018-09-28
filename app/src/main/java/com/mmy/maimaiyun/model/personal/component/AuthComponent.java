package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.AuthModule;
import com.mmy.maimaiyun.model.personal.ui.activity.AuthActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/12/11 0011 14:32
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = AuthModule.class,dependencies = AppComponent.class)
public interface AuthComponent {
    void inject(AuthActivity authActivity);
}
