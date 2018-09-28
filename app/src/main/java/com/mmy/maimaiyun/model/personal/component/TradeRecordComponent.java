package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.TradeRecordModule;
import com.mmy.maimaiyun.model.personal.ui.activity.TradeRecordActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/19 0019 11:47
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = TradeRecordModule.class,dependencies = AppComponent.class)
public interface TradeRecordComponent {
    void inject(TradeRecordActivity activity);
}
