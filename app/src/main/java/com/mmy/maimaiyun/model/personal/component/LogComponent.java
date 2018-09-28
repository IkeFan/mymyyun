package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.LogModule;
import com.mmy.maimaiyun.model.personal.ui.activity.LogActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/12/30 0030 14:50
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = LogModule.class,dependencies = AppComponent.class)
public interface LogComponent {
    void inject(LogActivity activity);
}
