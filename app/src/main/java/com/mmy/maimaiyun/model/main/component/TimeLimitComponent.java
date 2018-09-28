package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.TimeLimitModule;
import com.mmy.maimaiyun.model.main.ui.activity.TimeLimitActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 17:45
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = TimeLimitModule.class,dependencies = AppComponent.class)
public interface TimeLimitComponent {
    void inject(TimeLimitActivity activity);
}
