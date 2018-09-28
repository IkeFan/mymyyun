package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.AddCarModule;
import com.mmy.maimaiyun.model.personal.ui.activity.AddCarActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/11/15 0015 15:34
 * @描述
 */
@ActivityScoped
@Component(modules = AddCarModule.class,dependencies = AppComponent.class)
public interface AddCarComponent {
    void inject(AddCarActivity addCarActivity);
}
