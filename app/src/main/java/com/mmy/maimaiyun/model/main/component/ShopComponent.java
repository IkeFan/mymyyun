package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.ShopModule;
import com.mmy.maimaiyun.model.main.ui.activity.ShopActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2018/1/30 0030 14:09
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = ShopModule.class,dependencies = AppComponent.class)
public interface ShopComponent {
    void inject(ShopActivity activity);
}
