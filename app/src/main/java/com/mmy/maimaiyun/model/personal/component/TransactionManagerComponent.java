package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.TransactionManagerModule;
import com.mmy.maimaiyun.model.personal.ui.activity.TransactionManagerActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/21 0021 17:49
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = TransactionManagerModule.class,dependencies = AppComponent.class)
public interface TransactionManagerComponent {
    void inject(TransactionManagerActivity activity);
}
