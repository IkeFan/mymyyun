package com.mmy.maimaiyun.model.msg.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.msg.module.MsgModule;
import com.mmy.maimaiyun.model.msg.ui.activity.MsgActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/18 0018 10:46
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = MsgModule.class,dependencies = AppComponent.class)
public interface MsgComponent {
    void inject(MsgActivity activity);
}
