package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.OnlyNewModule;
import com.mmy.maimaiyun.model.main.ui.activity.OnlyNewActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2018/1/17 0017 19:23
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = {OnlyNewModule.class},dependencies = AppComponent.class)
public interface OnlyNewComponent {
    void inject(OnlyNewActivity activity);
}
