package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.SpreadCenterModeule;
import com.mmy.maimaiyun.model.personal.ui.activity.SpreadCenterActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/11/16 0016 16:29
 * @描述
 */
@ActivityScoped
@Component(modules = SpreadCenterModeule.class,dependencies = AppComponent.class)
public interface SpreadCenterComonent {
    void inject(SpreadCenterActivity activity);
}
