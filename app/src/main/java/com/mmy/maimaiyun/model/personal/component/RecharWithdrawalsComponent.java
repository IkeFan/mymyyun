package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.RecharWithdrawalsModule;
import com.mmy.maimaiyun.model.personal.ui.activity.RechargeWithdrawalsActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/11/17 0017 16:05
 * @描述
 */
@ActivityScoped
@Component(modules = RecharWithdrawalsModule.class,dependencies = AppComponent.class)
public interface RecharWithdrawalsComponent {
    void inject(RechargeWithdrawalsActivity activity);
}
