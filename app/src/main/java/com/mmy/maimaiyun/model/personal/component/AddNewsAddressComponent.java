package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.AddNewsAddressModule;
import com.mmy.maimaiyun.model.personal.ui.activity.AddNewAddressActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/6 0006 17:00
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = AddNewsAddressModule.class,dependencies = AppComponent.class)
public interface AddNewsAddressComponent {
    void inject(AddNewAddressActivity addNewAddressActivity);
}
