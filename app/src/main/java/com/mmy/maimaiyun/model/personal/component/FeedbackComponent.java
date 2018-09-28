package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.FeedbackModule;
import com.mmy.maimaiyun.model.personal.ui.activity.FeedbackActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/11/1 0001 15:24
 * @描述
 */
@ActivityScoped
@Component(modules = FeedbackModule.class,dependencies = AppComponent.class)
public interface FeedbackComponent {
    void inject(FeedbackActivity activity);
}
