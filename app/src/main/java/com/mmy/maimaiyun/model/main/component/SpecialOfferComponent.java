package com.mmy.maimaiyun.model.main.component;

import com.mmy.maimaiyun.AppComponent;
import com.mmy.maimaiyun.base.scoped.ActivityScoped;
import com.mmy.maimaiyun.model.main.module.SpecialOfferModule;
import com.mmy.maimaiyun.model.main.ui.activity.SpecialOfferActivity;

import dagger.Component;

/**
 * @创建者 lucas
 * @创建时间 2017/9/25 0025 15:34
 * @描述 TODO
 */
@ActivityScoped
@Component(modules = SpecialOfferModule.class,dependencies = AppComponent.class)
public interface SpecialOfferComponent {
    void inject(SpecialOfferActivity activity);
}
