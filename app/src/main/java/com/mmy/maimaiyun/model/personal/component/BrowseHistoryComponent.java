package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.BrowseHistoryModule;
import com.mmy.maimaiyun.model.personal.ui.activity.BrowseHistoryActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 9:49
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = BrowseHistoryModule.class,dependencies = AppComponent.class)
public interface BrowseHistoryComponent {
    void inject(BrowseHistoryActivity activity);
}
