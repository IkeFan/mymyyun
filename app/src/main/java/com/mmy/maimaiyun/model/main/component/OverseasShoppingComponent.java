package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.OverseasShoppingModule;
import com.mmy.maimaiyun.model.main.ui.activity.OverseasShoppingActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 16:17
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = OverseasShoppingModule.class,dependencies = AppComponent.class)
public interface OverseasShoppingComponent {
    void inject(OverseasShoppingActivity activity);
}
