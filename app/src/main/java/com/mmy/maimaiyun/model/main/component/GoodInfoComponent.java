package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.GoodInfoModule;
import com.mmy.maimaiyun.model.main.ui.activity.GoodInfoActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/5 0005 11:18
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = GoodInfoModule.class,dependencies = AppComponent.class)
public interface GoodInfoComponent {
    void inject(GoodInfoActivity activity);
}
