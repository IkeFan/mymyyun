package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.OneOneModule;
import com.mmy.maimaiyun.model.main.ui.activity.OneOneActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/12/1 0001 15:28
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = OneOneModule.class,dependencies = AppComponent.class)
public interface OneOneComponent {
    void inject(OneOneActivity activity);
}
