package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.OrderModule;
import com.mmy.maimaiyun.model.personal.ui.activity.OrderActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/7 0007 18:14
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = {OrderModule.class},dependencies = AppComponent.class)
public interface OrderComponent {
    void inject(OrderActivity activity);
}
