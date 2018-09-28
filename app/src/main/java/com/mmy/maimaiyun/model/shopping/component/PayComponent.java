package com.mmy.maimaiyun.model.shopping.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.shopping.module.PayModule;
import com.mmy.maimaiyun.model.shopping.ui.activity.PayActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 10:33
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = PayModule.class,dependencies = AppComponent.class)
public interface PayComponent {
    void inject(PayActivity activity);
}
