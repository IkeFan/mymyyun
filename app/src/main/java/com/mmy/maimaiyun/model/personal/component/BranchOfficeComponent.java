package com.mmy.maimaiyun.model.personal.component;


import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.BranchOfficeModule;
import com.mmy.maimaiyun.model.personal.ui.activity.BranchOfficeActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/10/25 0025 17:59
 * @描述
 */
@ActivityScoped
@Component(modules = BranchOfficeModule.class,dependencies = AppComponent.class)
public interface BranchOfficeComponent {
    void  inject(BranchOfficeActivity activity);
}
