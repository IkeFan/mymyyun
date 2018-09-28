package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.CouponModule;
import com.mmy.maimaiyun.model.personal.ui.activity.CouponActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/13 0013 14:34
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = CouponModule.class,dependencies = AppComponent.class)
public interface CouponComponent {
    void inject(CouponActivity activity);
}
