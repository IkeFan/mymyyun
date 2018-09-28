package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.NewGoodModule;
import com.mmy.maimaiyun.model.main.ui.activity.NewGoodsActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2018/1/18 0018 17:35
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = {NewGoodModule.class},dependencies = AppComponent.class)
public interface NewGoodComponent {
    void inject(NewGoodsActivity activity);
}
