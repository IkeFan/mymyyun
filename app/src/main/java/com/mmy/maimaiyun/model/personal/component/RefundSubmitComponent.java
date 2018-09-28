package com.mmy.maimaiyun.model.personal.component;


import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.RefundSubmitModule;
import com.mmy.maimaiyun.model.personal.ui.activity.RefundSubmitActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/12/5 0005 17:55
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = RefundSubmitModule.class,dependencies = AppComponent.class)
public interface RefundSubmitComponent {
    void inject(RefundSubmitActivity activity);
}
