package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.BankCarModule;
import com.mmy.maimaiyun.model.personal.ui.activity.BankCarActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 15:44
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = BankCarModule.class,dependencies = AppComponent.class)
public interface BankCarComponent {
    void inject(BankCarActivity activity);
}
