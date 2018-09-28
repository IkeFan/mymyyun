package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.HotGoodsModule;
import com.mmy.maimaiyun.model.main.ui.activity.HotGoodsActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/2 0002 10:02
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = HotGoodsModule.class,dependencies = AppComponent.class)
public interface HotGoodsCommend {
    void inject(HotGoodsActivity activity);
}
