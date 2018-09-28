package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.GoodNewsModule;
import com.mmy.maimaiyun.model.main.ui.activity.GoodNewsActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/4 0004 9:56
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = GoodNewsModule.class,dependencies = AppComponent.class)
public interface GoodNewsComponent {
    void inject(GoodNewsActivity activity);
}
