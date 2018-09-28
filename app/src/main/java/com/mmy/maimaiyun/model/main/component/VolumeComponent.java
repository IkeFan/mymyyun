package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.VolumeModule;
import com.mmy.maimaiyun.model.main.ui.activity.VolumeActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 15:26
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = VolumeModule.class,dependencies = AppComponent.class)
public interface VolumeComponent {
    void inject(VolumeActivity activity);
}
