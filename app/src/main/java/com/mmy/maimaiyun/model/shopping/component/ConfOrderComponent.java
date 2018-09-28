package com.mmy.maimaiyun.model.shopping.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.shopping.module.ConfOrderModule;
import com.mmy.maimaiyun.model.shopping.ui.activity.ConfOrderActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/6 0006 15:15
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = ConfOrderModule.class,dependencies = AppComponent.class)
public interface ConfOrderComponent {
    void inject(ConfOrderActivity activity);
}
