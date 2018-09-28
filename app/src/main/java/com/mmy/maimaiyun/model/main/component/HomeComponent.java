package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.FragmentScoped;
import com.mmy.maimaiyun.model.main.module.HomeModule;
import com.mmy.maimaiyun.model.main.ui.fragment.HomeFragment;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/8/17 0017 16:19
 * @描述 TODO
 */
@FragmentScoped
@Component(modules = {HomeModule.class},dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}
