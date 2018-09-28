package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.ResetPwdModule;
import com.mmy.maimaiyun.model.personal.ui.activity.ResetPwdActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017-11-08 02-14-06
 * @描述 修改密码组件
 */
@ActivityScoped
@Component(modules = ResetPwdModule.class,dependencies = AppComponent.class)
public interface ResetPwdComponent {
    void inject(ResetPwdActivity view);
}
