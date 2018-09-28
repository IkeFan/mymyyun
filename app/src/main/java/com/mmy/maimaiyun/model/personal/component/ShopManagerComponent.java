package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.ShopManagerModule;
import com.mmy.maimaiyun.model.personal.ui.activity.ShopManagerActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/11/24 0024 16:21
 * @描述
 */
@ActivityScoped
@Component(modules = ShopManagerModule.class,dependencies = AppComponent.class)
public interface ShopManagerComponent {
    void inject(ShopManagerActivity activity);
}
