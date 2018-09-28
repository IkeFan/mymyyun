package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.DDCardActivity;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.DDCardModule;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/12/14 0014 14:52
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = DDCardModule.class,dependencies = AppComponent.class)
public interface DDCardComponent {
    void inject(DDCardActivity activity);
}
