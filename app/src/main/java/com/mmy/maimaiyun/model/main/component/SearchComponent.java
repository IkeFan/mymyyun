package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.SearchModule;
import com.mmy.maimaiyun.model.main.ui.activity.SearchActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017-11-10 03-27-54
 * @描述 搜索组件
 */
@ActivityScoped
@Component(modules = SearchModule.class,dependencies = AppComponent.class)
public interface SearchComponent {
    void inject(SearchActivity view);
}
