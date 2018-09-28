package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.ShopModule;
import com.mmy.maimaiyun.model.personal.ui.activity.ShopActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 9:54
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = ShopModule.class,dependencies = AppComponent.class)
public interface ShopComponent {
    void inject(ShopActivity activity);
}
