package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.GoodManageModule;
import com.mmy.maimaiyun.model.personal.ui.activity.GoodManageActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/16 0016 15:17
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = GoodManageModule.class,dependencies = AppComponent.class)
public interface GoodManageComponent {
    void inject(GoodManageActivity activity);
}
