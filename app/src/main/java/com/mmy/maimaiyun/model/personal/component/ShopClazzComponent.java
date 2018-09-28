package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.ShopClazzModule;
import com.mmy.maimaiyun.model.personal.ui.activity.ShopClazzActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/20 0020 10:52
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = ShopClazzModule.class,dependencies = AppComponent.class)
public interface ShopClazzComponent {
    void inject(ShopClazzActivity activity);
}
