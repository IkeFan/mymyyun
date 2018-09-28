package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.PersonalInfoModule;
import com.mmy.maimaiyun.model.personal.ui.activity.PersonalInfoActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/18 0018 16:41
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = PersonalInfoModule.class,dependencies = AppComponent.class)
public interface PersonalInfoComponent {
    void inject(PersonalInfoActivity activity);
}
