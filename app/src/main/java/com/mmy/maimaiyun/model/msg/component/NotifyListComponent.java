package com.mmy.maimaiyun.model.msg.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.msg.module.NotifyListModule;
import com.mmy.maimaiyun.model.msg.ui.activity.NotifyListActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 13:52
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = NotifyListModule.class,dependencies = AppComponent.class)
public interface NotifyListComponent {
    void inject(NotifyListActivity activity);
}
