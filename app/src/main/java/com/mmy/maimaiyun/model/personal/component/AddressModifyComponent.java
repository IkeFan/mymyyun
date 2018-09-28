package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.AddressModifyModule;
import com.mmy.maimaiyun.model.personal.ui.activity.AddressModifyActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/7 0007 14:26
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = AddressModifyModule.class,dependencies = AppComponent.class)
public interface AddressModifyComponent {
    void inject(AddressModifyActivity addressModifyActivity);
}
