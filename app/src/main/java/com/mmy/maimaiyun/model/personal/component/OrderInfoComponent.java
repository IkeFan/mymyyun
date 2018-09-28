package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.OrderInfoModule;
import com.mmy.maimaiyun.model.personal.ui.activity.OrderInfoActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/11 0011 16:31
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = OrderInfoModule.class,dependencies = AppComponent.class)
public interface OrderInfoComponent {
    void inject(OrderInfoActivity activity);
}
