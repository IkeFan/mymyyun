package com.mmy.maimaiyun.model.personal.component;


import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.CommentModule;
import com.mmy.maimaiyun.model.personal.ui.activity.CommentActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/12/6 0006 11:53
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = CommentModule.class,dependencies = AppComponent.class)
public interface CommentComponent {
    void inject(CommentActivity activity);
}
