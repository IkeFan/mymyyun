package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.AllCommentModule;
import com.mmy.maimaiyun.model.main.ui.activity.AllCommentActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/12 0012 11:53
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = AllCommentModule.class, dependencies = AppComponent.class)
public interface AllCommentComponent {
    void inject(AllCommentActivity allCommentActivity);
}
