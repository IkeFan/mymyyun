package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.GoodsModule;
import com.mmy.maimaiyun.model.main.ui.activity.GoodsActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 11:20
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = GoodsModule.class,dependencies = AppComponent.class)
public interface GoodsComponent {
    void inject(GoodsActivity activity);
}
