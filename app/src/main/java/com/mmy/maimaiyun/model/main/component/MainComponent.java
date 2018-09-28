package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.MainModule;
import com.mmy.maimaiyun.model.main.ui.activity.MainActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/23 0023 16:23
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = MainModule.class,dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
