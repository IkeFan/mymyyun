package com.mmy.maimaiyun.model.personal.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.personal.module.FindSourceOfGoodsModule;
import com.mmy.maimaiyun.model.personal.ui.activity.FindSourceOfGoodsActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/1 0001 11:20
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = FindSourceOfGoodsModule.class,dependencies = AppComponent.class)
public interface FindSourceOfGoodsComponent {
    void inject(FindSourceOfGoodsActivity activity);
}
