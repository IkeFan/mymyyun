package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.RefundListModule;
import com.mmy.maimaiyun.model.personal.ui.activity.RefundListActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 11:08
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = RefundListModule.class,dependencies = AppComponent.class)
public interface RefundListComponent {
    void inject(RefundListActivity activity);
}
