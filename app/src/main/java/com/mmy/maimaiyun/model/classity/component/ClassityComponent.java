package com.mmy.maimaiyun.model.classity.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.FragmentScoped;
import com.mmy.maimaiyun.model.classity.module.ClassityModule;
import com.mmy.maimaiyun.model.classity.ui.fragment.ClassityFragment;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/24 0024 16:22
 * @描述 TODO
 */
@FragmentScoped
@Component(modules = ClassityModule.class,dependencies = AppComponent.class)
public interface ClassityComponent {
    void inject(ClassityFragment fragment);
}
