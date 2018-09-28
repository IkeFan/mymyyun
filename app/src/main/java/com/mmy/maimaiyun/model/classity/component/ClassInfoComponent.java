package com.mmy.maimaiyun.model.classity.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.classity.module.ClassInfoModule;
import com.mmy.maimaiyun.model.classity.ui.activity.ClassInfoActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/14 0014 18:02
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = ClassInfoModule.class,dependencies = AppComponent.class)
public interface ClassInfoComponent {
    void inject(ClassInfoActivity activity);
}
