package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.JoinApplyModule;
import com.mmy.maimaiyun.model.personal.ui.activity.JoinApplyActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/19 0019 11:53
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = JoinApplyModule.class,dependencies = AppComponent.class)
public interface JoinApplyComponent {
    void inject(JoinApplyActivity applyActivity);
}
