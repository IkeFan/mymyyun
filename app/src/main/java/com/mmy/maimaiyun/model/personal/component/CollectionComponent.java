package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.CollectionModule;
import com.mmy.maimaiyun.model.personal.ui.activity.CollectionActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/8 0008 13:50
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = CollectionModule.class,dependencies = AppComponent.class)
public interface CollectionComponent {
    void inject(CollectionActivity activity);
}
