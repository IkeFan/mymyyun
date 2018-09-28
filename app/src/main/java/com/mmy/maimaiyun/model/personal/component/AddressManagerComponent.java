package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.AddressManagerModule;
import com.mmy.maimaiyun.model.personal.ui.activity.AddressManagerActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 10:15
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = AddressManagerModule.class,dependencies = AppComponent.class)
public interface AddressManagerComponent {
    void inject(AddressManagerActivity activity);
}
